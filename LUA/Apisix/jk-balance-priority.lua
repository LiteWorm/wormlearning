local core = require("apisix.core")
local ipairs = ipairs


local _M = {}


local function max_priority(a, b)
    return a > b
end


function _M.new(up_nodes, upstream, picker_mod)
    local priority_index = up_nodes._priority_index
    core.table.sort(priority_index, max_priority)

    local pickers = core.table.new(#priority_index, 0)
    for i, priority in ipairs(priority_index) do
        local picker, err = picker_mod.new(up_nodes[priority], upstream)
        if not picker then
            return nil, "failed to create picker with priority " .. priority .. ": " .. err
        end
        if not picker.before_retry_next_priority then
            return nil, "picker should define 'before_retry_next_priority' to reset ctx"
        end

        pickers[i] = picker
    end

    return {
        upstream = upstream,
        get = function (ctx)
            for i = ctx.priority_balancer_picker_idx or 1, #pickers do
                local picker = pickers[i]
                local server, err = picker.get(ctx)
                if server then
                    ctx.priority_balancer_picker_idx = i
                    return server
                end

                core.log.notice("failed to get server from current priority ",
                                priority_index[i],
                                ", try next one, err: ", err)

                picker.before_retry_next_priority(ctx)
            end

            return nil, "all servers tried"
        end,
        after_balance = function (ctx, before_retry)
            local priority_balancer_picker = pickers[ctx.priority_balancer_picker_idx]
            if not priority_balancer_picker or
                not priority_balancer_picker.after_balance
            then
                return
            end

            priority_balancer_picker.after_balance(ctx, before_retry)
        end
    }
end


return _M
