package com.liteworm;

import com.liteworm.domain.Student;
import com.liteworm.service.StudentService;
import com.liteworm.service.impl.StudentServiceImpl;
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
    private StudentService studentService = new StudentServiceImpl();

    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;

        try {
            inputStream = Resources.getResourceAsStream(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        Student stu  = session.selectOne("queryById", 1);
        System.out.println(stu);
        System.out.println("****************************");

        List<Student> sList = session.selectList("queryAll");
        for (Student student: sList) {
            System.out.println(student);
        }


        Student ssins = new Student();
        ssins.setAge(29);
        ssins.setName("ssss");
        ssins.setId("8");
        //session.insert("addStudent", ssins);

        //session.update("updateStudent",ssins);


        session.delete("deleteStudent", "8");
        session.commit();
        session.close();

    }
}