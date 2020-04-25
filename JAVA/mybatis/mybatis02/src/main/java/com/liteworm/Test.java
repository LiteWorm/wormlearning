package com.liteworm;

import com.liteworm.domain.Student;
import com.liteworm.service.StudentService;
import com.liteworm.service.impl.StudentServiceImpl;
import com.liteworm.utils.ServiceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName Test
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 20:25
 * @Version 1.0
 **/
public class Test {


    public static void main(String[] args) {
        //StudentService service = new StudentServiceImpl();
        StudentService service = (StudentService)ServiceFactory.getService(new StudentServiceImpl());
        Student student = new Student();
        student.setName("ceshi");
        student.setAge(66);

        //service.save(student);

        Student student1 = service.queryStudentById("16");
        System.out.println(student1);

    }
}