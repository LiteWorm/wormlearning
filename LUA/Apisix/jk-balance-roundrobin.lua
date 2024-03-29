local roundrobin  = require("resty.roundrobin")
local core = require("apisix.core")
local nkeys = core.table.nkeys
local pairs = pairs


local _M = {}


function _M.new(up_nodes, upstream)
    local safe_limit = 0
    for _, weight in pairs(up_nodes) do
        safe_limit = safe_limit + weight + 1
    end

    local picker = roundrobin:new(up_nodes)
    local nodes_count = nkeys(up_nodes)
    return {
        upstream = upstream,
        get = function (ctx)
            if ctx.balancer_tried_servers and ctx.balancer_tried_servers_count == nodes_count then
                return nil, "all upstream servers tried"
            end

            local server, err
            for i = 1, safe_limit do
                server, err = picker:find()
                if not server then
                    return nil, err
                end
                if ctx.balancer_tried_servers then
                    if not ctx.balancer_tried_servers[server] then
                        break
                    end
                else
                    break
                end
            end

            return server
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
