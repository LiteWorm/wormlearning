package com.eadlon.testdemo.service;

import com.eadlon.testdemo.entity.TestCountData;

/**
 * @author liuyong
 */
public interface TestDbConnectService {
    public TestCountData getDataByAppId(String appId);
}
