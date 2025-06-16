package com.liteworm.service.impl;

import com.liteworm.beans.Student;
import com.liteworm.dao.StudentDao;
import com.liteworm.service.StudentService;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 23:30
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {
    //定义依赖对象Dao
    private StudentDao stuDao;

    //使用ioc，设置注入，在配置文件中给dao赋值
    public void setStuDao(StudentDao stuDao) {
        this.stuDao = stuDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = stuDao.insetStrudent(student);
        return nums;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = stuDao.selectStudents();
        return students;
    }
}