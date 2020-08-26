package com.liteworm.tspb.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String account;
    private  String passWord;
    private String userName;
    private String mobile;
    private String email;
    private String status;
   // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String creator;
   // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String modifier;
    private String remark;
}
