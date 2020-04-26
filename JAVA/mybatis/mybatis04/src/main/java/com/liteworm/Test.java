package com.liteworm;

import com.liteworm.domain.Student;
import com.liteworm.service.StudentService;
import com.liteworm.service.impl.StudentServiceImpl;
import com.liteworm.utils.ServiceFactory;

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
//        Student student = new Student();
//        student.setId("A00004");
//        student.setName("ceshi4");
//        student.setAge(66);
//
//        //service.save(student);
//
//        Student student1 = service.queryStudentById("A00003");
//        System.out.println(student1);

        //查询所有记录
        List<Student> sList = service.getAll();
        for(Student s : sList){
            System.out.println(s);
        }
    }
}