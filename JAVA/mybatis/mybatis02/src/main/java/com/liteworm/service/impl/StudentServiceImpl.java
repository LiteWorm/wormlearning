package com.liteworm.service.impl;

import com.liteworm.dao.StudentDao;
import com.liteworm.dao.impl.StudentDaoImpl;
import com.liteworm.domain.Student;
import com.liteworm.service.StudentService;

/**
 * @ClassName StudentServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 20:17
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student queryStudentById(String id) {
        Student stu = studentDao.getById(id);

        return  stu;
    }

    @Override
    public void save(Student student) {

        studentDao.save(student);

    }
}