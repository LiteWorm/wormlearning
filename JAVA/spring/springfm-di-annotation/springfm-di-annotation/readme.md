使用注解创建对象，给属性赋值
用注解实现DI依赖注入

准备工作：
1.需要spring-aop.jar
2.spring 的配置文件加入spring-context.xsd的约束文件

步骤：
1.新建maven
2.加入spring的依赖，必选有spring-aop.jar
3.新建类
    在勒种加入注解
4.新建spring配置文件
    申明组件扫描标签，指定注解所在的包
5.新建测试了类