package com.liteworm.tspb.service;


import com.liteworm.tspb.entity.User;

public interface UserOperService {
    void getAll();
    int addUser(User user);
}

