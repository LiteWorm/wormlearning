package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.dao;

/**
 * @ClassName MysqlDaoImpl
 * @Decription
 * 定义操作MySql数据库的类
 * @AUthor LiteWorm
 * @Date 2020/3/29 14:11
 * @Version 1.0
 **/
public class MysqlDaoImpl implements DaoService {
    @Override
    public void add() {
        System.out.println("向Mysql添加数据");
    }

    @Override
    public void delete() {
        System.out.println("在Mysql删除数据");
    }

    @Override
    public void update() {
        System.out.println("在Mysql更新数据");
    }

    @Override
    public void listAll() {
        System.out.println("展示Mysql所有数据");
    }
}
