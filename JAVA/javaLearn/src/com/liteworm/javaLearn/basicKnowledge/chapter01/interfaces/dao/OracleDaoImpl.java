package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.dao;

/**
 * @ClassName OracleDaoImpl
 * @Decription
 * @AUthor LiteWorm
 * @Date 2020/3/29 14:18
 * @Version 1.0
 **/
public class OracleDaoImpl implements DaoService {
    @Override
    public void add() {
        System.out.println("向Oracle添加数据");
    }

    @Override
    public void delete() {
        System.out.println("在Oracle删除数据");
    }

    @Override
    public void update() {
        System.out.println("在Oracle更新数据");
    }

    @Override
    public void listAll() {
        System.out.println("展示Oracle所有数据");
    }
}
