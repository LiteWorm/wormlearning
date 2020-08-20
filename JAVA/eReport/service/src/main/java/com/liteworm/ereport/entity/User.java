package com.liteworm.ereport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String account;
    private String passwd;
    private String userName;
    private String mobile;

    public User(){
    }


}
