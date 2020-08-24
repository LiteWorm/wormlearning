package com.liteworm.tspb.com.liteworm.tspb.entity;

import lombok.Data;

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
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String modifier;
    private String remark;
}
