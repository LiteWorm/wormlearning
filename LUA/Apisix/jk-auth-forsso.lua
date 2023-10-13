local core      = require("apisix.core")
local http      = require "resty.http"
local sub_str   = string.sub
local type      = type
local ngx       = ngx
local plugin_name = "authz-keycloak"

local log = core.log
local pairs = pairs

local schema = {
    type = "object",
    properties = {
        discovery = {type = "string", minLength = 1, maxLength = 4096},
        token_endpoint = {type = "string", minLength = 1, maxLength = 4096},
        resource_registration_endpoint = {type = "string", minLength = 1, maxLength = 4096},
        client_id = {type = "string", minLength = 1, maxLength = 100},
        client_secret = {type = "string", minLength = 1, maxLength = 100},
        grant_type = {
            type = "string",
            default="urn:ietf:params:oauth:grant-type:uma-ticket",
            enum = {"urn:ietf:params:oauth:grant-type:uma-ticket"},
            minLength = 1, maxLength = 100
        },
        policy_enforcement_mode = {
            type = "string",
            enum = {"ENFORCING", "PERMISSIVE"},
            default = "ENFORCING"
        },
        permissions = {
            type = "array",
            items = {
                type = "string",
                minLength = 1, maxLength = 100
            },
            uniqueItems = true,
            default = {}
        },
        lazy_load_paths = {type = "boolean", default = false},
        http_method_as_scope = {type = "boolean", default = false},
        timeout = {type = "integer", minimum = 1000, default = 3000},
        ssl_verify = {type = "boolean", default = true},
        cache_ttl_seconds = {type = "integer", minimum = 1, default = 24 * 60 * 60},
        keepalive = {type = "boolean", default = true},
        keepalive_timeout = {type = "integer", minimum = 1000, default = 60000},
        keepalive_pool = {type = "integer", minimum = 1, default = 5},
        access_denied_redirect_uri = {type = "string", minLength = 1, maxLength = 2048},
        access_token_expires_in = {type = "integer", minimum = 1, default = 300},
        access_token_expires_leeway = {type = "integer", minimum = 0, default = 0},
        refresh_token_expires_in = {type = "integer", minimum = 1, default = 3600},
        refresh_token_expires_leeway = {type = "integer", minimum = 0, default = 0},
        password_grant_token_generation_incoming_uri = {
            type = "string",
            minLength = 1,
            maxLength = 4096
        },
    },
    required = {"client_id"},
    allOf = {
        {
            anyOf = {
                {required = {"discovery"}},
                {required = {"token_endpoint"}}
            }
        },
        {
            anyOf = {
                {
                    properties = {
                        lazy_load_paths = {enum = {false}},
                    }
                },
                {
                    properties = {
                        lazy_load_paths = {enum = {true}},
                    },
                    anyOf = {
                        {required = {"discovery"}},
                        {required = {"resource_registration_endpoint"}}
                    }
                }
            }
        }
    }
}


local _M = {
    version = 0.1,
    priority = 2000,
    name = plugin_name,
    schema = schema,
}


function _M.check_schema(conf)
    return core.schema.check(schema, conf)
end



local function authz_keycloak_cache_get(type, key)
    local dict = ngx.shared[type]
    local value
    if dict then
        value = dict:get(key)
        if value then log.debug("cache hit: type=", type, " key=", key) end
    end
    return value
end


local function authz_keycloak_cache_set(type, key, value, exp)
    local dict = ngx.shared[type]
    if dict and (exp > 0) then
        local success, err, forcible = dict:set(key, value, exp)
        if err then
            log.error("cache set: success=", success, " err=", err, " forcible=", forcible)
        else
            log.debug("cache set: success=", success, " err=", err, " forcible=", forcible)
        end
    end
end


local function authz_keycloak_configure_params(params, conf)
    if conf.keepalive then
        params.keepalive_timeout = conf.keepalive_timeout
        params.keepalive_pool = conf.keepalive_pool
    else
        params.keepalive = conf.keepalive
    end

    params.ssl_verify = conf.ssl_verify

    return conf.http_request_decorator and conf.http_request_decorator(params) or params
end


local function authz_keycloak_configure_timeouts(httpc, timeout)
    if timeout then
        if type(timeout) == "table" then
            httpc:set_timeouts(timeout.connect or 0, timeout.send or 0, timeout.read or 0)
        else
            httpc:set_timeout(timeout)
        end
    end
end


local function authz_keycloak_configure_proxy(httpc, proxy_opts)
    if httpc and proxy_opts and type(proxy_opts) == "table" then
        log.debug("authz_keycloak_configure_proxy : use http proxy")
        httpc:set_proxy_options(proxy_opts)
    else
        log.debug("authz_keycloak_configure_proxy : don't use http proxy")
    end
end


local function authz_keycloak_get_http_client(conf)
    local httpc = http.new()
    authz_keycloak_configure_timeouts(httpc, conf.timeout)
    authz_keycloak_configure_proxy(httpc, conf.proxy_opts)
    return httpc
end



local function authz_keycloak_parse_json_response(response)
    local err
    local res

    if response.status ~= 200 then
        err = "response indicates failure, status=" .. response.status .. ", body=" .. response.body
    else
        res, err = core.json.decode(response.body)

        if not res then
            err = "JSON decoding failed: " .. err
        end
    end

    return res, err
end


local function authz_keycloak_discover(conf)
    log.debug("authz_keycloak_discover: URL is: " .. conf.discovery)

    local json, err
    local v = authz_keycloak_cache_get("discovery", conf.discovery)

    if not v then
        log.debug("Discovery data not in cache, making call to discovery endpoint.")

        local httpc = authz_keycloak_get_http_client(conf)

        local params = authz_keycloak_configure_params({}, conf)

        local res, error = httpc:request_uri(conf.discovery, params)

        if not res then
            err = "Accessing discovery URL (" .. conf.discovery .. ") failed: " .. error
            log.error(err)
        else
            log.debug("Response data: " .. res.body)
            json, err = authz_keycloak_parse_json_response(res)
            if json then
                authz_keycloak_cache_set("discovery", conf.discovery, core.json.encode(json),
                                         conf.cache_ttl_seconds)
            else
                err = "could not decode JSON from Discovery data" .. (err and (": " .. err) or '')
                log.error(err)
            end
        end
    else
        json = core.json.decode(v)
    end

    return json, err
end


local function authz_keycloak_ensure_discovered_data(conf)
    local err
    if type(conf.discovery) == "string" then
        local discovery
        discovery, err = authz_keycloak_discover(conf)
        if not err then
            conf.discovery = discovery
        end
    end
    return err
end


local function authz_keycloak_get_endpoint(conf, endpoint)
    if conf and conf[endpoint] then
        return conf[endpoint]
    elseif conf and conf.discovery and type(conf.discovery) == "table" then
        return conf.discovery[endpoint]
    end

    return nil
end


local function authz_keycloak_get_token_endpoint(conf)
    return authz_keycloak_get_endpoint(conf, "token_endpoint")
end


local function authz_keycloak_get_resource_registration_endpoint(conf)
    return authz_keycloak_get_endpoint(conf, "resource_registration_endpoint")
end


local function authz_keycloak_access_token_expires_in(conf, expires_in)
    return (expires_in or conf.access_token_expires_in)
           - 1 - conf.access_token_expires_leeway
end


local function authz_keycloak_refresh_token_expires_in(conf, expires_in)
    return (expires_in or conf.refresh_token_expires_in)
           - 1 - conf.refresh_token_expires_leeway
end


local function authz_keycloak_ensure_sa_access_token(conf)
    local client_id = conf.client_id
    local ttl = conf.cache_ttl_seconds
    local token_endpoint = authz_keycloak_get_token_endpoint(conf)

    if not token_endpoint then
        log.error("Unable to determine token endpoint.")
        return 503, "Unable to determine token endpoint."
    end

    local session = authz_keycloak_cache_get("access-tokens", token_endpoint .. ":"
                                             .. client_id)

    if session then
        local err
        session, err = core.json.decode(session)

        if not session then
            return 500, err
        end

        local current_time = ngx.time()

        if current_time < session.access_token_expiration then
            log.debug("Access token is still valid.")
            return session.access_token
        else
            log.debug("Access token has expired.")
            if session.refresh_token
               and (not session.refresh_token_expiration
                    or current_time < session.refresh_token_expiration) then
                -- Try to get a new access token, using the refresh token.
                log.debug("Trying to get new access token using refresh token.")

                local httpc = authz_keycloak_get_http_client(conf)

                local params = {
                    method = "POST",
                    body = ngx.encode_args({
                        grant_type = "refresh_token",
                        client_id = client_id,
                        client_secret = conf.client_secret,
                        refresh_token = session.refresh_token,
                    }),
                    headers = {
                        ["Content-Type"] = "application/x-www-form-urlencoded"
                    }
                }

                params = authz_keycloak_configure_params(params, conf)

                local res, err = httpc:request_uri(token_endpoint, params)

                if not res then
                    err = "Accessing token endpoint URL (" .. token_endpoint
                          .. ") failed: " .. err
                    log.error(err)
                    return nil, err
                end

                log.debug("Response data: " .. res.body)
                local json, err = authz_keycloak_parse_json_response(res)

                if not json then
                    err = "Could not decode JSON from token endpoint"
                          .. (err and (": " .. err) or '.')
                    log.error(err)
                    return nil, err
                end

                if not json.access_token then
                    log.debug("Answer didn't contain a new access token. Clearing session.")
                    session = nil
                else
                    log.debug("Got new access token.")
                    session.access_token = json.access_token

                    session.access_token_expiration = current_time
                            + authz_keycloak_access_token_expires_in(conf, json.expires_in)

                    if json.refresh_token ~= nil then
                        log.debug("Got new refresh token.")
                        session.refresh_token = json.refresh_token

                        session.refresh_token_expiration = current_time
                                + authz_keycloak_refresh_token_expires_in(conf,
                                                                          json.refresh_expires_in)
                    end

                    authz_keycloak_cache_set("access-tokens",
                                             token_endpoint .. ":" .. client_id,
                                             core.json.encode(session), ttl)
                end
            else
                log.debug("No or expired refresh token. Clearing session.")
                session = nil
            end
        end
    end

    if not session then

        log.debug("Getting access token for Protection API from token endpoint.")
        local httpc = authz_keycloak_get_http_client(conf)

        local params = {
            method = "POST",
            body = ngx.encode_args({
                grant_type = "client_credentials",
                client_id = client_id,
                client_secret = conf.client_secret,
            }),
            headers = {
                ["Content-Type"] = "application/x-www-form-urlencoded"
            }
        }

        params = authz_keycloak_configure_params(params, conf)

        local current_time = ngx.time()

        local res, err = httpc:request_uri(token_endpoint, params)

        if not res then
            err = "Accessing token endpoint URL (" .. token_endpoint .. ") failed: " .. err
            log.error(err)
            return nil, err
        end

        log.debug("Response data: " .. res.body)
        local json, err = authz_keycloak_parse_json_response(res)

        if not json then
          err = "Could not decode JSON from token endpoint" .. (err and (": " .. err) or '.')
          log.error(err)
          return nil, err
        end

        if not json.access_token then
            err = "Response does not contain access_token field."
            log.error(err)
            return nil, err
        end

        session = {}

        session.access_token = json.access_token

        session.access_token_expiration = current_time
                + authz_keycloak_access_token_expires_in(conf, json.expires_in)

        if json.refresh_token ~= nil then
            session.refresh_token = json.refresh_token

            session.refresh_token_expiration = current_time
                    + authz_keycloak_refresh_token_expires_in(conf, json.refresh_expires_in)
        end

        authz_keycloak_cache_set("access-tokens", token_endpoint .. ":" .. client_id,
                                 core.json.encode(session), ttl)
    end

    return session.access_token
end


local function authz_keycloak_resolve_resource(conf, uri, sa_access_token)
    local resource_registration_endpoint = authz_keycloak_get_resource_registration_endpoint(conf)

    if not resource_registration_endpoint then
        local err = "Unable to determine registration endpoint."
        log.error(err)
        return 503, err
    end

    log.debug("Resource registration endpoint: ", resource_registration_endpoint)

    local httpc = authz_keycloak_get_http_client(conf)

    local params = {
        method = "GET",
        query = {uri = uri, matchingUri = "true"},
        headers = {
            ["Authorization"] = "Bearer " .. sa_access_token
        }
    }

    params = authz_keycloak_configure_params(params, conf)

    local res, err = httpc:request_uri(resource_registration_endpoint, params)

    if not res then
        err = "Accessing resource registration endpoint URL (" .. resource_registration_endpoint
              .. ") failed: " .. err
        log.error(err)
        return nil, err
    end

    log.debug("Response data: " .. res.body)
    res.body = '{"resources": ' .. res.body .. '}'
    local json, err = authz_keycloak_parse_json_response(res)

    if not json then
      err = "Could not decode JSON from resource registration endpoint"
            .. (err and (": " .. err) or '.')
      log.error(err)
      return nil, err
    end

    return json.resources
end


local function evaluate_permissions(conf, ctx, token)
    local err = authz_keycloak_ensure_discovered_data(conf)
    if err then
        return 503, err
    end

    local permission

    if conf.lazy_load_paths then
        local sa_access_token, err = authz_keycloak_ensure_sa_access_token(conf)
        if err then
            log.error(err)
            return 503
        end

        permission, err = authz_keycloak_resolve_resource(conf, ctx.var.request_uri,
                                                          sa_access_token)

        if permission == nil then
            log.error(err)
            return 503
        end
    else
        permission = conf.permissions
    end

    if #permission == 0 and conf.policy_enforcement_mode == "ENFORCING" then
        if conf.access_denied_redirect_uri then
            core.response.set_header("Location", conf.access_denied_redirect_uri)
            return 307
        end
        return 403, '{"error":"access_denied","error_description":"not_authorized"}'
    end

    local scope
    if conf.http_method_as_scope then
        scope = ctx.var.request_method
    end

    if scope then
        for k, v in pairs(permission) do
            if v:find("#", 1, true) then
                permission[k] = v .. ", " .. scope
            else
                permission[k] = v .. "#" .. scope
            end
        end
    end

    for k, v in pairs(permission) do
        log.debug("Requesting permission ", v, ".")
    end

    local token_endpoint = authz_keycloak_get_token_endpoint(conf)
    if not token_endpoint then
        err = "Unable to determine token endpoint."
        log.error(err)
        return 503, err
    end
    log.debug("Token endpoint: ", token_endpoint)

    local httpc = authz_keycloak_get_http_client(conf)

    local params = {
        method = "POST",
        body = ngx.encode_args({
            grant_type = conf.grant_type,
            audience = conf.client_id,
            response_mode = "decision",
            permission = permission
        }),
        headers = {
            ["Content-Type"] = "application/x-www-form-urlencoded",
            ["Authorization"] = token
        }
    }

    params = authz_keycloak_configure_params(params, conf)

    local res, err = httpc:request_uri(token_endpoint, params)

    if not res then
        err = "Error while sending authz request to " .. token_endpoint .. ": " .. err
        log.error(err)
        return 503
    end

    log.debug("Response status: ", res.status, ", data: ", res.body)

    if res.status == 403 then
        log.debug('Request denied: HTTP 403 Forbidden. Body: ', res.body)
        if conf.access_denied_redirect_uri then
            core.response.set_header("Location", conf.access_denied_redirect_uri)
            return 307
        end

        return res.status, res.body
    elseif res.status == 401 then
        log.debug('Request denied: HTTP 401 Unauthorized. Body: ', res.body)
        return res.status, res.body
    elseif res.status >= 400 then
        log.error('Request denied: Token endpoint returned an error (status: ',
                  res.status, ', body: ', res.body, ').')
        return res.status, res.body
    end

end


local function fetch_jwt_token(ctx)
    local token = core.request.header(ctx, "Authorization")
    if not token then
        return nil, "authorization header not available"
    end

    local prefix = sub_str(token, 1, 7)
    if prefix ~= 'Bearer ' and prefix ~= 'bearer ' then
        return "Bearer " .. token
    end
    return token
end

local function generate_token_using_password_grant(conf,ctx)
    log.debug("generate_token_using_password_grant Function Called")

    local body, err = core.request.get_body()
    if err or not body then
        log.error("Failed to get request body: ", err)
        return 503
    end
    local parameters = core.string.decode_args(body)

    local username = parameters["username"]
    local password = parameters["password"]

    if not username then
        local err = "username is missing."
        log.warn(err)
        return 422, {message = err}
    end
    if not password then
        local err = "password is missing."
        log.warn(err)
        return 422, {message = err}
    end

    local client_id = conf.client_id

    local token_endpoint = authz_keycloak_get_token_endpoint(conf)

    if not token_endpoint then
        local err = "Unable to determine token endpoint."
        log.error(err)
        return 503, {message = err}
    end
    local httpc = authz_keycloak_get_http_client(conf)

    local params = {
        method = "POST",
        body = ngx.encode_args({
            grant_type = "password",
            client_id = client_id,
            client_secret = conf.client_secret,
            username = username,
            password = password
        }),
        headers = {
            ["Content-Type"] = "application/x-www-form-urlencoded"
        }
    }

    params = authz_keycloak_configure_params(params, conf)

    local res, err = httpc:request_uri(token_endpoint, params)

    if not res then
        err = "Accessing token endpoint URL (" .. token_endpoint
              .. ") failed: " .. err
        log.error(err)
        return 401, {message = "Accessing token endpoint URL failed."}
    end

    log.debug("Response data: " .. res.body)
    local json, err = authz_keycloak_parse_json_response(res)

    if not json then
        err = "Could not decode JSON from response"
              .. (err and (": " .. err) or '.')
        log.error(err)
        return 401, {message = "Could not decode JSON from response."}
    end

    return res.status, res.body
end

function _M.access(conf, ctx)
    local headers = core.request.headers(ctx)
    local need_grant_token = conf.password_grant_token_generation_incoming_uri and
        ctx.var.request_uri == conf.password_grant_token_generation_incoming_uri and
        headers["content-type"] == "application/x-www-form-urlencoded" and
        core.request.get_method() == "POST"
    if need_grant_token then
        return generate_token_using_password_grant(conf,ctx)
    end
    log.debug("hit keycloak-auth access")
    local jwt_token, err = fetch_jwt_token(ctx)
    if not jwt_token then
        log.error("failed to fetch JWT token: ", err)
        return 401, {message = "Missing JWT token in request"}
    end

    local status, body = evaluate_permissions(conf, ctx, jwt_token)
    if status then
        return status, body
    end
end


return _M
