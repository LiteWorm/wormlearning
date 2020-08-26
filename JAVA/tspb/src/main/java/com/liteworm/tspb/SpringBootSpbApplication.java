package com.liteworm.tspb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.liteworm.tspb.dao")
@SpringBootApplication
public class SpringBootSpbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpbApplication.class, args);
    }
}
