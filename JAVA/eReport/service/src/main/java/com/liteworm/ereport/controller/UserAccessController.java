package com.liteworm.ereport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {
    @PostMapping("/login")
    public String userLogin(){
        String logResult = null;
        logResult = "登录成功！";
        return logResult;
    }

    @PostMapping("/logout")
    public String userLogOut(){
        String logResult = null;
        logResult = "退出成功！";
        return logResult;
    }

    @PostMapping("/uls")
    public String logStatusUpdate(){
        String logResult = null;
        logResult = "更新成功！";
        return logResult;
    }

}
