package com.liteworm.ereport.service;

import com.liteworm.ereport.entity.User;

import java.util.List;

public interface UsersOperateService {
    List<User> selAll();
    int insertUser(User user);

}
