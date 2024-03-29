
local require = require
local core = require("apisix.core")
local timers = require("apisix.timers")
local plugin = require("apisix.plugin")

local ngx_time = ngx.time
local ngx_timer_at = ngx.timer.at
local ngx_worker_id = ngx.worker.id
local type = type

local load_time = os.time()
local plugin_name = "get-service-info"
local default_report_interval = 60
local default_report_ttl = 7200

local schema = {
    type = "object",
}
local attr_schema = {
    type = "object",
    properties = {
        report_interval = {
            type = "integer",
            description = "server info reporting interval (unit: second)",
            default = default_report_interval,
            minimum = 60,
            maximum = 3600,
        },
        report_ttl = {
            type = "integer",
            description = "live time for server info in etcd",
            default = default_report_ttl,
            minimum = 3600,
            maximum = 86400,
        }
    }
}

local internal_status = ngx.shared["internal-status"]
if not internal_status then
    error("lua_shared_dict \"internal-status\" not configured")
end


local _M = {
    version = 0.1,
    priority = 2,
    name = plugin_name,
    schema = schema,
}


local function get_boot_time()
    local time, err = internal_status:get("server_info:boot_time")
    if err ~= nil then
        core.log.error("failed to get boot_time from shdict: ", err)
        return load_time
    end

    if time ~= nil then
        return time
    end

    local _, err = internal_status:set("server_info:boot_time", load_time)
    if err ~= nil then
        core.log.error("failed to save boot_time to shdict: ", err)
    end

    return load_time
end


local function uninitialized_server_info()
    local boot_time = get_boot_time()
    return {
        etcd_version     = "unknown",
        hostname         = core.utils.gethostname(),
        id               = core.id.get(),
        version          = core.version.VERSION,
        up_time          = ngx_time() - boot_time,
        boot_time        = boot_time,
        last_report_time = -1,
    }
end


local function get()
    local data, err = internal_status:get("server_info")
    if err ~= nil then
        return nil, err
    end

    if not data then
        return uninitialized_server_info()
    end

    local server_info, err = core.json.decode(data)
    if not server_info then
        return nil, err
    end

    server_info.up_time = ngx_time() - server_info.boot_time
    return server_info
end


local function get_server_info()
    local info, err = get()
    if not info then
        core.log.error("failed to get server_info: ", err)
        return 500
    end

    return 200, info
end


local function report(premature, report_ttl)
    if premature then
        return
    end

    local server_info, err = get()
    if not server_info then
        core.log.error("failed to get server_info: ", err)
        return
    end

    if server_info.etcd_version == "unknown" then
        local res, err = core.etcd.server_version()
        if not res then
            core.log.error("failed to fetch etcd version: ", err)
            return

        elseif type(res.body) ~= "table" then
            core.log.error("failed to fetch etcd version: bad version info")
            return

        else
            server_info.etcd_version = res.body.etcdcluster
        end
    end

    server_info.last_report_time = ngx_time()

    local key = "/data_plane/server_info/" .. server_info.id
    local ok, err = core.etcd.set(key, server_info, report_ttl)
    if not ok then
        core.log.error("failed to report server info to etcd: ", err)
        return
    end

    local data, err = core.json.encode(server_info)
    if not data then
        core.log.error("failed to encode server_info: ", err)
        return
    end

    local ok, err = internal_status:set("server_info", data)
    if not ok then
        core.log.error("failed to encode and save server info: ", err)
        return
    end
end


function _M.check_schema(conf)
    local ok, err = core.schema.check(schema, conf)
    if not ok then
        return false, err
    end

    return true
end


function _M.control_api()
    return {
        {
            methods = {"GET"},
            uris ={"/v1/server_info"},
            handler = get_server_info,
        }
    }
end


function _M.init()
    core.log.info("server info: ", core.json.delay_encode(get()))

    if core.config ~= require("apisix.core.config_etcd") then
        -- we don't need to report server info if etcd is not in use.
        return
    end

    local attr = plugin.plugin_attr(plugin_name)
    local ok, err = core.schema.check(attr_schema, attr)
    if not ok then
        core.log.error("failed to check plugin_attr: ", err)
        return
    end

    local report_ttl = attr and attr.report_ttl or default_report_ttl
    local report_interval = attr and attr.report_interval or default_report_interval
    local start_at = ngx_time()

    local fn = function()
        local now = ngx_time()
        if now - start_at >= report_interval then
            start_at = now
            report(nil, report_ttl)
        end
    end

    if ngx_worker_id() == 0 then
        local ok, err = ngx_timer_at(0, report, report_ttl)
        if not ok then
            core.log.error("failed to create initial timer to report server info: ", err)
            return
        end
    end

    timers.register_timer("plugin#server-info", fn, true)

    core.log.info("timer created to report server info, interval: ",
                  report_interval)
end


function _M.destroy()
    timers.unregister_timer("plugin#server-info", true)
end


return _M
