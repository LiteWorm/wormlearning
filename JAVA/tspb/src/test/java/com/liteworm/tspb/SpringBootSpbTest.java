package com.liteworm.tspb;

import com.liteworm.tspb.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSpbTest {

    Logger logger = LoggerFactory.getLogger(SpringBootSpbTest.class);

    @Autowired
    private Student student;

    @Value("${test.value.configeration}")
    private String tvalue;

    @Test
    public void testReadYaml(){
        logger.info(student.toString());
    }

    @Test
    public void testValue(){
        logger.info(tvalue);
    }

}
