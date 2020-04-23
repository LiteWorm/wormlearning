package com.liteworm.action;


import com.liteworm.beans.Student;
import com.liteworm.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RegisterServlet
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/24 0:15
 * @Version 1.0
 **/
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strName = req.getParameter("name");
        String strAge = req.getParameter("age");
        String config = "applicationContext.xml";
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
//        System.out.println("容器对象：" + ctx.getClass().getName());

        //获取ServletContext中的容器对象，spring提供了一个方法，获取容器对象
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        System.out.println("容器对象：" + ctx.getClass().getName());
        //从容器中获取service
        StudentService service = (StudentService) ctx.getBean("studentService");
        Student stu = new Student();
        stu.setAge(Integer.parseInt(strAge));

        stu.setName(strName);
        System.out.println(stu);
        service.addStudent(stu);
        req.getRequestDispatcher("/result.jsp").forward(req,resp);

    }
}