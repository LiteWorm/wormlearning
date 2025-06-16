package com.liteworm.dao;

import com.liteworm.beans.Student;

import java.util.List;

/**
 * @ClassName StudentDao
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 23:07
 * @Version 1.0
 **/
public interface OrderDao {
    int insetStrudent(Student student);
    List<Student> selectStudents();
}