package com.liteworm.service.impl;

import com.liteworm.dao.StudentDao;
import com.liteworm.domain.Student;
import com.liteworm.service.StudentService;
import com.liteworm.utils.SqlSessionUtil;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 20:17
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);

    @Override
    public Student queryStudentById(String id) {
        Student stu = studentDao.getById(id);

        return  stu;
    }

    @Override
    public void save(Student student) {

        studentDao.save(student);

    }

    @Override
    public List<Student> getAll() {
        List<Student>  studentList = studentDao.getAll();
        return studentList;
    }
}