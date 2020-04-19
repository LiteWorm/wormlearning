package com.liteworm.ba04.service.impl;

import com.liteworm.ba04.beans.User;
import com.liteworm.ba04.dao.UserDao;
import com.liteworm.ba04.service.UserService;

/**
 * @ClassName UserServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 12:55
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }
}