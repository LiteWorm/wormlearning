local core        = require("apisix.core")
local resty_chash = require("resty.chash")
local str_char    = string.char
local str_gsub    = string.gsub
local pairs = pairs


local CONSISTENT_POINTS = 160   -- points per server, taken from `resty.chash`


local _M = {}


local function fetch_chash_hash_key(ctx, upstream)
    local key = upstream.key
    local hash_on = upstream.hash_on or "vars"
    local chash_key

    if hash_on == "consumer" then
        chash_key = ctx.consumer_name
    elseif hash_on == "vars" then
        chash_key = ctx.var[key]
    elseif hash_on == "header" then
        chash_key = ctx.var["http_" .. key]
    elseif hash_on == "cookie" then
        chash_key = ctx.var["cookie_" .. key]
    elseif hash_on == "vars_combinations" then
        local err, n_resolved
        chash_key, err, n_resolved = core.utils.resolve_var(key, ctx.var);
        if err then
            core.log.error("could not resolve vars in ", key, " error: ", err)
        end

        if n_resolved == 0 then
            chash_key = nil
        end
    end

    if not chash_key then
        chash_key = ctx.var["remote_addr"]
        core.log.warn("chash_key fetch is nil, use default chash_key ",
                      "remote_addr: ", chash_key)
    end
    core.log.info("upstream key: ", key)
    core.log.info("hash_on: ", hash_on)
    core.log.info("chash_key: ", core.json.delay_encode(chash_key))

    return chash_key
end


function _M.new(up_nodes, upstream)
    local str_null = str_char(0)

    local nodes_count = 0
    local safe_limit = 0
    local servers, nodes = {}, {}
    for serv, weight in pairs(up_nodes) do
        local id = str_gsub(serv, ":", str_null)

        nodes_count = nodes_count + 1
        safe_limit = safe_limit + weight
        servers[id] = serv
        nodes[id] = weight
    end
    safe_limit = safe_limit * CONSISTENT_POINTS

    local picker = resty_chash:new(nodes)
    return {
        upstream = upstream,
        get = function (ctx)
            local id
            if ctx.balancer_tried_servers then
                if ctx.balancer_tried_servers_count == nodes_count then
                    return nil, "all upstream servers tried"
                end

                for i = 1, safe_limit do
                    id, ctx.chash_last_server_index = picker:next(ctx.chash_last_server_index)
                    if not ctx.balancer_tried_servers[servers[id]] then
                        break
                    end
                end
            else
                local chash_key = fetch_chash_hash_key(ctx, upstream)
                id, ctx.chash_last_server_index = picker:find(chash_key)
            end
            
            return servers[id]
        end,
        after_balance = function (ctx, before_retry)
            if not before_retry then
                if ctx.balancer_tried_servers then
                    core.tablepool.release("balancer_tried_servers", ctx.balancer_tried_servers)
                    ctx.balancer_tried_servers = nil
                end

                return nil
            end

            if not ctx.balancer_tried_servers then
                ctx.balancer_tried_servers = core.tablepool.fetch("balancer_tried_servers", 0, 2)
            end

            ctx.balancer_tried_servers[ctx.balancer_server] = true
            ctx.balancer_tried_servers_count = (ctx.balancer_tried_servers_count or 0) + 1
        end,
        before_retry_next_priority = function (ctx)
            if ctx.balancer_tried_servers then
                core.tablepool.release("balancer_tried_servers", ctx.balancer_tried_servers)
                ctx.balancer_tried_servers = nil
            end

            ctx.balancer_tried_servers_count = 0
        end,
    }
end


return _M
