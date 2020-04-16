package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.dao;
/*
* @auther LiteWorm
* @Description
* 定义给个操作数据库的接口
* 封装一族操作数据库的规范
* @Date 14:05 2020/3/29
* @Param
* @return
**/
public interface DaoService {
    void add();
    void delete();
    void update();
    void listAll();

}
