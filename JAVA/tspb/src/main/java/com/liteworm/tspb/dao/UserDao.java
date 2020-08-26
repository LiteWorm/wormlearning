package com.liteworm.tspb.dao;

import com.liteworm.tspb.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface UserDao {

    @Results(id = "UserDaoMap" ,
             value = {@Result(column = "create_time", property = "createTime"),
                     @Result(column = "user_name", property = "userName"),
                     @Result(column = "update_time", property = "updateTime")
             })

    @ResultMap(value = "UserDaoMap")
    List<User> selectAll();

    int addUser(User user);
}
