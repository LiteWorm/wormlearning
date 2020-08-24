package com.liteworm.tspb.com.liteworm.tspb.controller;

import com.liteworm.tspb.com.liteworm.tspb.dao.UserDao;
import com.liteworm.tspb.com.liteworm.tspb.entity.Student;
import com.liteworm.tspb.com.liteworm.tspb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    UserDao userDao ;
    @Autowired
    private Student student;

    @RequestMapping("/crt/getmessage")
    public String getMessage(){

        System.out.println(student);
        return "Test Controller";
    }

    @RequestMapping("/getall")
    public List<User>  getAll(){

        List<User > sts = null;
        sts = userDao.selectAll();
        return sts;

    }
}
