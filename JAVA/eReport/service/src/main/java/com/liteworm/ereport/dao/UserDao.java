package com.liteworm.ereport.dao;

import com.liteworm.ereport.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> selAll();
    int insertUser(UserDao user);
}
