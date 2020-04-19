package com.liteworm.service;

import com.liteworm.beans.Student;

import java.util.List;

/**
 * @ClassName StudentService
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 23:29
 * @Version 1.0
 **/
public interface StudentService {
    int addStudent(Student student);
    List<Student> queryStudents();
}
