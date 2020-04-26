package com.liteworm.dao;

import com.liteworm.domain.Student;

import java.util.List;
import java.util.Map;

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

    Student select1(String s);

    List<Student> select2(int i);

    List<Student> select3(String ss, int i);

    List<Student> select4(Student s);

    List<Student> select5(Map<String, Object> map);

    List<Student> select6(String s);

    List<Student> select7(String p);

    List<Student> select8(String p);

    List<Student> select9(String p);

    String select10(String s);

    List<String> select11();

    int select12();

    List<Map<String, Object>> select14();

    List<Student> select16();
}
