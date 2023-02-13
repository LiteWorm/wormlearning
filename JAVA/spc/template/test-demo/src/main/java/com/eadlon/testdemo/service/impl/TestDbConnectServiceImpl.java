package com.eadlon.testdemo.service.impl;

import com.eadlon.testdemo.entity.TestCountData;
import com.eadlon.testdemo.mapper.TestCountDataMapper;
import com.eadlon.testdemo.service.TestDbConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyong
 */
@Slf4j
@Service
public class TestDbConnectServiceImpl implements TestDbConnectService {

    @Autowired
    private TestCountDataMapper testCountDataMapper ;

    public TestCountData getDataByAppId(String appId){

        TestCountData testCountData = testCountDataMapper.querryByAppid(appId);

        return testCountData;
    }

}
