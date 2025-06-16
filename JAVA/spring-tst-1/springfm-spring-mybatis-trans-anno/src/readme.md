Spring整合Mybatis,搭建事务的环境，使用注解控制事务

使用注解的步骤：
    1.修改审批让的配置文件
        1）声明事务管理器，管理器完成事务的commit，rollback
        2）声明事务的注解驱动，告诉框架，使用注解完成事务的设置
    2.在Service的实现类的public方法智商加入@Transaction

步骤：
    1.新建mysql的库，sale,goods
    2.加入依赖
        1）spring依赖
        2）mybatis的依赖
        3）mybatis-spring依赖，这个jar是从mybatis官网下载的，mybatis提供在spring中创建对象的类。
        4）mysql的驱动
        druid，数据库连接池的依赖
    3.新建实体类Sale,Goods
    4.新建Dao解耦和sql映射文件
    5.新建mybatis主配置文件
    6.新建Service接口和实现类，在实现类中有Dao的属性
    7.新建spring的配置文件
        1）声明数据源DataSource对象
        2）声明SqlSessionFactoryBean，创建SqlSessionFactory对象
        3）声明MyBatis的稻苗器，创建Dao借楼的实现类对象
        4）声明自定义的Service，把3）中的Dao对象注入赋值给Service的属性
    8.新建测试类，从Spring容器中获取Service，调用Service的方法，完成数据库的操作