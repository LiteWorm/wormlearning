package com.liteworm.tspb.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Data
@Component
//@ConfigurationProperties比搜啊还是和yml配置文件对应，读取其中的数据
//prifix属性表示和yml配置文件中以“student”开头的配置项对应
@ConfigurationProperties(prefix = "student")
public class Student {
    @Value("${student.stu-id}")
    private Integer stuID;
    private String stuName;
    private Boolean granduated;
    private String[] subject;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private Map<String, String> teachers;
    private Address address;
}
