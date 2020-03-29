package com.liteworm.javaLearn.chapter01.interfaces.dao;

/**
 * @ClassName Test
 * @Decription
 * 测试项目分层
 * @AUthor LiteWorm
 * @Date 2020/3/29 14:12
 * @Version 1.0
 **/
public class Test {


    public static void main(String[] args) {
//        DaoService daoService = new MysqlDaoImpl();
        DaoService daoService = new OracleDaoImpl();
        Logic01 logic1 = new Logic01(daoService);
        logic1.addData();
        logic1.deleteData();
        logic1.aupdateData();
        logic1.listData();
    }


}
