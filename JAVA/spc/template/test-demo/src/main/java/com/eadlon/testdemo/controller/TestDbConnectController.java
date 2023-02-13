package com.eadlon.testdemo.controller;

import com.eadlon.testdemo.entity.TestCountData;
import com.eadlon.testdemo.service.TestDbConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuyong
 */

@Slf4j
@RestController
@RequestMapping("/query")
public class TestDbConnectController {

    @Autowired
    private TestDbConnectService testDbConnectService;
    @RequestMapping("id")
    public TestCountData TestDBConnectController(){
        log.error("TestDBConnectController");
        return testDbConnectService.getDataByAppId("300011862576");
    }

}
