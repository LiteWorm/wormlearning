local base_prometheus = require("prometheus")
local core      = require("apisix.core")
local ipairs    = ipairs
local ngx       = ngx
local ngx_capture = ngx.location.capture
local re_gmatch = ngx.re.gmatch
local select = select
local type = type
local prometheus
local router = require("apisix.router")
local get_routes = router.http_routes
local get_ssls   = router.ssls
local get_services = require("apisix.http.service").services
local get_consumers = require("apisix.consumer").consumers
local get_upstreams = require("apisix.upstream").upstreams
local clear_tab = core.table.clear
local get_stream_routes = router.stream_routes
local get_protos = require("apisix.plugins.grpc-transcode.proto").protos
local service_fetch = require("apisix.http.service").get
local latency_details = require("apisix.utils.log-util").latency_details_in_ms



local DEFAULT_BUCKETS = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 30000, 60000}

local metrics = {}

local inner_tab_arr = {}

local function gen_arr(...)
    clear_tab(inner_tab_arr)
    for i = 1, select('#', ...) do
        inner_tab_arr[i] = select(i, ...)
    end

    return inner_tab_arr
end


local _M = {}


function _M.init()
   
    if ngx.get_phase() ~= "init" and ngx.get_phase() ~= "init_worker"  then
        return
    end

    clear_tab(metrics)

 
    prometheus = base_prometheus.init("prometheus-metrics", "apisix_")
    metrics.connections = prometheus:gauge("nginx_http_current_connections",
            "Number of HTTP connections",
            {"state"})

    metrics.etcd_reachable = prometheus:gauge("etcd_reachable",
            "Config server etcd reachable from APISIX, 0 is unreachable")


    metrics.node_info = prometheus:gauge("node_info",
            "Info of APISIX node",
            {"hostname"})

    metrics.etcd_modify_indexes = prometheus:gauge("etcd_modify_indexes",
            "Etcd modify index for APISIX keys",
            {"key"})

   
    metrics.status = prometheus:counter("http_status",
            "HTTP status codes per service in APISIX",
            {"code", "route", "matched_uri", "matched_host", "service", "consumer", "node"})

    metrics.latency = prometheus:histogram("http_latency",
        "HTTP request latency in milliseconds per service in APISIX",
        {"type", "route", "service", "consumer", "node"}, DEFAULT_BUCKETS)

    metrics.bandwidth = prometheus:counter("bandwidth",
            "Total bandwidth in bytes consumed per service in APISIX",
            {"type", "route", "service", "consumer", "node"})

end


function _M.log(conf, ctx)
    local vars = ctx.var

    local route_id = ""
    local balancer_ip = ctx.balancer_ip or ""
    local service_id = ""
    local consumer_name = ctx.consumer_name or ""

    local matched_route = ctx.matched_route and ctx.matched_route.value
    if matched_route then
        route_id = matched_route.id
        service_id = matched_route.service_id or ""
        if conf.prefer_name == true then
            route_id = matched_route.name or route_id
            if service_id ~= "" then
                local service = service_fetch(service_id)
                service_id = service and service.value.name or service_id
            end
        end
    end

    local matched_uri = ""
    local matched_host = ""
    if ctx.curr_req_matched then
        matched_uri = ctx.curr_req_matched._path or ""
        matched_host = ctx.curr_req_matched._host or ""
    end

    metrics.status:inc(1,
        gen_arr(vars.status, route_id, matched_uri, matched_host,
                service_id, consumer_name, balancer_ip))

    local latency, upstream_latency, apisix_latency = latency_details(ctx)
    metrics.latency:observe(latency,
        gen_arr("request", route_id, service_id, consumer_name, balancer_ip))

    if upstream_latency then
        metrics.latency:observe(upstream_latency,
            gen_arr("upstream", route_id, service_id, consumer_name, balancer_ip))
    end

    metrics.latency:observe(apisix_latency,
        gen_arr("apisix", route_id, service_id, consumer_name, balancer_ip))

    metrics.bandwidth:inc(vars.request_length,
        gen_arr("ingress", route_id, service_id, consumer_name, balancer_ip))

    metrics.bandwidth:inc(vars.bytes_sent,
        gen_arr("egress", route_id, service_id, consumer_name, balancer_ip))
end


local ngx_status_items = {"active", "accepted", "handled", "total",
                         "reading", "writing", "waiting"}
local label_values = {}

local function nginx_status()
    local res = ngx_capture("/apisix/nginx_status")
    if not res or res.status ~= 200 then
        core.log.error("failed to fetch Nginx status")
        return
    end


    local iterator, err = re_gmatch(res.body, [[(\d+)]], "jmo")
    if not iterator then
        core.log.error("failed to re.gmatch Nginx status: ", err)
        return
    end

    for _, name in ipairs(ngx_status_items) do
        local val = iterator()
        if not val then
            break
        end

        label_values[1] = name
        metrics.connections:set(val[0], label_values)

    end
end


local key_values = {}
local function set_modify_index(key, items, items_ver, global_max_index)
    clear_tab(key_values)
    local max_idx = 0
    if items_ver and items then
        for _, item in ipairs(items) do
            if type(item) == "table" then
                local modify_index = item.orig_modifiedIndex or item.modifiedIndex
                if modify_index > max_idx then
                    max_idx = modify_index
                end
            end
        end
    end

    key_values[1] = key
    metrics.etcd_modify_indexes:set(max_idx, key_values)


    global_max_index = max_idx > global_max_index and max_idx or global_max_index

    return global_max_index
end


local function etcd_modify_index()
    clear_tab(key_values)
    local global_max_idx = 0

  
    local routes, routes_ver = get_routes()
    global_max_idx = set_modify_index("routes", routes, routes_ver, global_max_idx)

    
    local services, services_ver = get_services()
    global_max_idx = set_modify_index("services", services, services_ver, global_max_idx)

   
    local ssls, ssls_ver = get_ssls()
    global_max_idx = set_modify_index("ssls", ssls, ssls_ver, global_max_idx)

    
    local consumers, consumers_ver = get_consumers()
    global_max_idx = set_modify_index("consumers", consumers, consumers_ver, global_max_idx)

   
    local global_rules = router.global_rules
    if global_rules then
        global_max_idx = set_modify_index("global_rules", global_rules.values,
            global_rules.conf_version, global_max_idx)

     
        key_values[1] = "prev_index"
        metrics.etcd_modify_indexes:set(global_rules.prev_index, key_values)

    else
        global_max_idx = set_modify_index("global_rules", nil, nil, global_max_idx)
    end

   
    local upstreams, upstreams_ver = get_upstreams()
    global_max_idx = set_modify_index("upstreams", upstreams, upstreams_ver, global_max_idx)

   
    local stream_routes, stream_routes_ver = get_stream_routes()
    global_max_idx = set_modify_index("stream_routes", stream_routes,
        stream_routes_ver, global_max_idx)

  
    local protos, protos_ver = get_protos()
    global_max_idx = set_modify_index("protos", protos, protos_ver, global_max_idx)

   
    key_values[1] = "max_modify_index"
    metrics.etcd_modify_indexes:set(global_max_idx, key_values)

end


function _M.collect()
    if not prometheus or not metrics then
        core.log.error("prometheus: plugin is not initialized, please make sure ",
                     " 'prometheus_metrics' shared dict is present in nginx template")
        return 500, {message = "An unexpected error occurred"}
    end

   
    nginx_status()

    local config = core.config.new()

    
    local vars = ngx.var or {}
    local hostname = vars.hostname or ""

    if config.type == "etcd" then
     
        etcd_modify_index()

        local version, err = config:server_version()
        if version then
            metrics.etcd_reachable:set(1)

        else
            metrics.etcd_reachable:set(0)
            core.log.error("prometheus: failed to reach config server while ",
                           "processing metrics endpoint: ", err)
        end

     
        local res, _ = config:getkey("/phantomkey")
        if res and res.headers then
            clear_tab(key_values)
        
            key_values[1] = "x_etcd_index"
            metrics.etcd_modify_indexes:set(res.headers["X-Etcd-Index"], key_values)
        end
    end

    metrics.node_info:set(1, gen_arr(hostname))

    core.response.set_header("content_type", "text/plain")
    return 200, core.table.concat(prometheus:metric_data())
end


function _M.metric_data()
    return prometheus:metric_data()
end

function _M.get_prometheus()
    return prometheus
end

return _M
