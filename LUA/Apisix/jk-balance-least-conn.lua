local core = require("apisix.core")
local binaryHeap = require("binaryheap")
local ipairs = ipairs
local pairs = pairs


local _M = {}


local function least_score(a, b)
    return a.score < b.score
end


function _M.new(up_nodes, upstream)
    local servers_heap = binaryHeap.minUnique(least_score)
    for server, weight in pairs(up_nodes) do
        local score = 1 / weight
        servers_heap:insert({
            server = server,
            effect_weight = 1 / weight,
            score = score,
        }, server)
    end

    return {
        upstream = upstream,
        get = function (ctx)
            local server, info, err
            if ctx.balancer_tried_servers then
                local tried_server_list = {}
                while true do
                    server, info = servers_heap:peek()
                    if server == nil then
                        err = "all upstream servers tried"
                        break
                    end

                    if not ctx.balancer_tried_servers[server] then
                        break
                    end

                    servers_heap:pop()
                    core.table.insert(tried_server_list, info)
                end

                for _, info in ipairs(tried_server_list) do
                    servers_heap:insert(info, info.server)
                end
            else
                server, info = servers_heap:peek()
            end

            if not server then
                return nil, err
            end

            info.score = info.score + info.effect_weight
            servers_heap:update(server, info)
            return server
        end,
        after_balance = function (ctx, before_retry)
            local server = ctx.balancer_server
            local info = servers_heap:valueByPayload(server)
            info.score = info.score - info.effect_weight
            servers_heap:update(server, info)

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

            ctx.balancer_tried_servers[server] = true
        end,
        before_retry_next_priority = function (ctx)
            if ctx.balancer_tried_servers then
                core.tablepool.release("balancer_tried_servers", ctx.balancer_tried_servers)
                ctx.balancer_tried_servers = nil
            end
        end,
    }
end


return _M
