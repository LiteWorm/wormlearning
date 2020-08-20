package com.liteworm.ereport.controller;

import com.liteworm.ereport.entity.User;
import com.liteworm.ereport.service.UsersOperateService;
import com.liteworm.ereport.service.impl.UsersOperateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        log.debug("测试debug");
        log.error("测试error");
        log.info("测试info");
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/test01")
    public void test01(){
        UsersOperateService usos = new UsersOperateServiceImpl();
        List<User> users=usos.selAll();
       log.info(users.toString());
    }
}
