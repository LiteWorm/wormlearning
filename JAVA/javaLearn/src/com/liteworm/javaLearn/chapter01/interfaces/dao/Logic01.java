package com.liteworm.javaLearn.chapter01.interfaces.dao;

/**
 * @ClassName Logic01
 * @Decription
 * 业务逻辑模块
 * 通过接口事项对数据库的操作
 * @AUthor LiteWorm
 * @Date 2020/3/29 14:06
 * @Version 1.0
 **/
public class Logic01  {
    DaoService daoService;

    public Logic01(DaoService daoService) {
        this.daoService = daoService;
    }

    public void addData(){
        System.out.println("向数据库中插入数据");
        daoService.add();
    }
    public void deleteData(){
        System.out.println("向数据库中插入数据");
        daoService.delete();
    }
    public void aupdateData(){
        System.out.println("向数据库中插入数据");
        daoService.add();
    }
    public void listData(){
        System.out.println("向数据库中插入数据");
        daoService.add();
    }
}
