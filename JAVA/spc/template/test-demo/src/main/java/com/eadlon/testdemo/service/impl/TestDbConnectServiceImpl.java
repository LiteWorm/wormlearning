package com.eadlon.testdemo.service.impl;

import com.eadlon.testdemo.entity.TestCountData;
import com.eadlon.testdemo.mapper.TestCountDataMapper;
import com.eadlon.testdemo.service.TestDbConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuyong
 */
@Slf4j
@Service
public class TestDbConnectServiceImpl implements TestDbConnectService {

    private final TestCountDataMapper testCountDataMapper ;

    public TestDbConnectServiceImpl(TestCountDataMapper testCountDataMapper) {
        this.testCountDataMapper = testCountDataMapper;
    }

    public TestCountData getDataByAppId(String appId){

        return testCountDataMapper.querryByAppid(appId);
    }

}
