package com.liteworm.tspb.com.liteworm.tspb.dao;

import com.liteworm.tspb.com.liteworm.tspb.entity.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
}
