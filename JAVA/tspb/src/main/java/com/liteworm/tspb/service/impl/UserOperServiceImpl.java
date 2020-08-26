package com.liteworm.tspb.service.impl;

import com.liteworm.tspb.dao.UserDao;
import com.liteworm.tspb.entity.User;
import com.liteworm.tspb.service.UserOperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserOperServiceImpl implements UserOperService {
    private static Logger logger = LoggerFactory.getLogger(UserOperServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public void getAll() {
        logger.info("UserOperServiceImpl test");
        List<User> list = userDao.selectAll();
        for ( User us :  list){
            logger.info(us.toString());
        }
        logger.info("UserOperServiceImpl success!");
    }

    @Override
    public int addUser(User user) {
        userDao.addUser(user);
        return 0;
    }
}
