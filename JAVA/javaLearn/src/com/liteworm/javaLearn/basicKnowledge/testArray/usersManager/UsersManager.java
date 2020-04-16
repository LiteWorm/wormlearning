package com.liteworm.javaLearn.basicKnowledge.testArray.usersManager;

import java.util.Arrays;

/**
 * @ClassName UsersManager
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 13:49
 * @Version 1.0
 **/
public class UsersManager {
    private  Users[] usersess;
    private  int size;

    public UsersManager() {
        usersess = new Users[5];
    }

    public UsersManager(int size) {
        this.size = size;
        usersess = new Users[size];
    }

    public void  add(Users u){
        if (size >= usersess.length){
            usersess = Arrays.copyOf(usersess, usersess.length * 2);
        }
        usersess[size] = u;
        size ++;
    }

    /**
    * @auther LiteWorm
    * @ClassName UsersManager
    * @FunctionName userNmaeExist
    * @Description
     * 判断用户名是否存在
    * @Date 13:57 2020/4/5
    * @Param [userName]
    * @return boolean
    **/
    public boolean userNameExists(String userName){
        for (Users u :usersess) {
            if(u == null ){
               continue;
            } else if (userName != null && u.getUserName().equals(userName)) {
               return true;
            }
        }
        return  false;
    }

    /**
    * @auther LiteWorm
    * @ClassName UsersManager
    * @FunctionName userExists
    * @Description
     * 判断用户是否存在
    * @Date 13:59 2020/4/5
    * @Param [u]
    * @return boolean
    **/
    public  boolean userExists(Users u){
        for (Users ua: usersess){
            if(ua == null){
                continue;
            }
            if(u != null && ua.equals(u)){
                return true;
            }
        }
        return false;
    }


}
