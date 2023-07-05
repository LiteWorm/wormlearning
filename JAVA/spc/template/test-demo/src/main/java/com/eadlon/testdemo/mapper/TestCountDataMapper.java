package com.eadlon.testdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eadlon.testdemo.entity.TestCountData;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author liuyong
 */
@Component(value = "TestCountDataMapper")
public interface TestCountDataMapper extends BaseMapper<TestCountData> {

    //public TestCountData getDataAppid(String appid);
    @Select("select t.appid,t.ct,t.pkdate FROM test_count_data t " +
            "where t.appid = #{appid}" +
            "limit 1 ")
    public TestCountData querryByAppid(String appid);
}
