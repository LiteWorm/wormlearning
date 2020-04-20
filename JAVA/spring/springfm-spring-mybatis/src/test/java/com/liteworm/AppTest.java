package com.liteworm;

import static org.junit.Assert.assertTrue;

import com.liteworm.beans.Student;
import com.liteworm.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void testAddStudent(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        StudentService service =  (StudentService)ctx.getBean("studentService");
        Student student = new Student();
        student.setName("李四");
        student.setAge(22);
        int nums = service.addStudent(student);
        System.out.println("addStudent nums:" + nums);
    }

    @Test
    public void testQueryStudent(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        StudentService service =  (StudentService)ctx.getBean("studentService");
        List<Student> students = service.queryStudents();
        for (Student student:
             students) {
            System.out.println(student);

        }
    }
}
