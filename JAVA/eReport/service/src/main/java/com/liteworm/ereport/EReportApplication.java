
package com.liteworm.ereport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.liteworm.ereport.dao.mapper")
@RestController
class EReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(EReportApplication.class, args);
    }
}
