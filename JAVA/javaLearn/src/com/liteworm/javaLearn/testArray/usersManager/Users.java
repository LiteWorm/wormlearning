package com.liteworm.javaLearn.testArray.usersManager;

import java.util.Objects;

/**
 * @ClassName Users
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 13:48
 * @Version 1.0
 **/
public class Users {
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userName.equals(users.userName) &&
                passWord.equals(users.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passWord);
    }
}
