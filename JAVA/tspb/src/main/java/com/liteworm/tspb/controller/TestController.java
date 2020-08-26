package com.liteworm.tspb.controller;

import com.liteworm.tspb.entity.Student;
import com.liteworm.tspb.entity.User;
import com.liteworm.tspb.service.UserOperService;
import com.liteworm.tspb.service.impl.UserOperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@RestController
public class TestController {
    Logger  logger = LoggerFactory.getLogger(TestController.class);
    @Resource
    UserOperService userOperService;
    @Autowired
    private Student student;


    private Student student1 = new Student();

    @RequestMapping("/crt/getmessage")
    public String getMessage(){
        System.out.println(student);
        logger.debug(student1.toString());
        return "Test Controller";
    }

    @RequestMapping("/getall")
    public String  getAll(){
        logger.info("start!");
        userOperService.getAll();
        logger.info("end!");
        return "Success !!";

    }

    @RequestMapping("/adduser")
    public String addUser(){
        User user = new User();
        user.setAccount("test3");
        user.setPassWord("test3");
        user.setUserName("测试3");
        user.setMobile("13555555555");
        user.setEmail("test3@aspirecn.com");
        user.setStatus("1");
        user.setCreateTime(new Date());
        user.setCreator("admin");
        user.setModifier("admin");
        user.setUpdateTime(new Date());
        user.setRemark("无");
        logger.info(user.toString());
        userOperService.addUser(user);
        return  "Add Success!!";
    }

}
