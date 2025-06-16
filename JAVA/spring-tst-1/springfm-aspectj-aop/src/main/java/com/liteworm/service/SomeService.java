package com.liteworm.service;

import com.liteworm.beans.Other;
import com.liteworm.beans.Student;

/**
 * @ClassName SomeService
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 21:28
 * @Version 1.0
 **/
public interface SomeService {
    void doSome(String name, int age, Other other);
    void doSome(String name, int age);
    String doOther();
    Student doOther2();

    String  doFirst(String name, int age);

    void doSecond(String name, int age);

    void doThird();
}