package com.liteworm.dao;

import com.liteworm.domain.Student;

import java.util.List;

/**
 * @ClassName StudentDao
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 22:48
 * @Version 1.0
 **/
public interface StudentDao {
    public Student getById(String id);
    public void save(Student student);

    public List<Student> getAll();
}
