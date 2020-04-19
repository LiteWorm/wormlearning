package com.liteworm.ba04.dao.impl;

import com.liteworm.ba04.beans.User;
import com.liteworm.ba04.dao.UserDao;

/**
 * @ClassName UserDaoImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 12:53
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser(User user) {
        System.out.println("添加用户:" + user);
    }
}