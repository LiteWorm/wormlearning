使用Spring框架实现aop
重要步骤：
    1、实现同时的接口，例如环绕通知MethodInterceptor
    2、在Spring的配置文件中，声明ProxyFactoryBean，创建目标对象的代理对象
        ProxyFactoryBean作用等同于jdk动态代理中的Proxy
步骤
    1、新建maven
    2、加入依赖
        1）spring的依赖
    3、新建业务接口和实现类。要给业务法方法增强功能
    4、新建实现接口的类（InvocationHandler）
        在类中重写方法，实现切面的功能代码
    5、新建spring的配置文件
        1）声明目标类对象
        2）声明切面类对象
        3）声明ProxyFactoryBean，创建代理对象
    6、新建测试类，从spring容器中获取对象，通过这个对象执行业务放啊发，实现功能的增强

