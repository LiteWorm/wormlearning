package com.liteworm.ba01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName Student
 * @Decription
 * @Component:创建类的对象，等同于<bean/>，默认是单例对象
 *     属性：value 标识对象的名称（<bean/> 的id）
 *     位置：在类定义的尚明，标识创建此类的对象
 *
 *     @Component(value = "myStudent") 等价于
 *     <bean id = "myStudent" class="com.liteworm.ba01.Student"/>
 *
 * 和 @Component功能相同的其他注解
 * 1、@Repository：放在Dao层的实现类上面，创建dao对象。这样的对象是访问数据库的
 * 2、@Service：放在Service层的实现类上面，创建service对象，标识业务层对象，能够有事务功能
 * 3、@Controller：放在处理器类的上面，创建处理器对象，处理器对象能接收用户的请求。
 * @Repository，@Service，@Controller都能创建对象，除此之外还有其他的功能。
 * 使用这三个注解，能够对应用进行分层。
 *
 * @AUthor LiteWorm
 * @Date 2020/4/18 16:21
 * @Version 1.0
 **/

//创建Student的对象，名称叫myStudent，对象存放在spring容器中
//  @Component(value = "myStudent")


//省略value
//    @Component("myStudent2")


//不指定对象的名称，框架会给对象默认名称，默认的值是类名，首字母小写
//    @Component


@Component("myStudent")
@Repository
public class Student {

    private  String name;
    private  int age;

    public Student() {
        System.out.println("使用注解方式调用默认构造");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}