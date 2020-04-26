package com.liteworm;

import com.liteworm.dao.StudentDao;
import com.liteworm.domain.Student;
import com.liteworm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Test2
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/26 21:42
 * @Version 1.0
 **/
public class Test2 {
    public static void main(String[] args) {
        StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);

        //1.测试：parameterType 使用简单数据类型：String
//        Student s1 = studentDao.select1("1");
//        System.out.println(s1);

        //2.测试：parameterType 使用简单数据类型：int
  /*      List<Student> sList = studentDao.select2(24);
        for (Student s: sList  ) {
            System.out.println(s);
        }*/

        //3.测试parameterType
        //查询姓名+age
        //绝对不可以对sql传递多个参数
//        List<Student> students = studentDao.select3("ss", 24);

        //如果要为sql语句传递多个参数，则需要将参数封装到一个domain包中，或者是打包到一个map中

        //4.测试parameterType 使用domain为参数
/*        Student s = new Student();
        s.setName("ppp");
        s.setAge(24);
        List<Student> students = studentDao.select4(s);
        for(Student ss : students){
            System.out.println(ss);
        }*/

        //5.测试parameterType 使用Map为参数
     /*   Map<String,Object> map = new HashMap<>();
        map.put("name", "ppp");
        map.put("age", 24);
        List<Student> ss = studentDao.select5(map);
        for(Student s: ss){
            System.out.println(s);
        }*/

        //6.测试parameterType
 /*       List<Student> s = studentDao.select6("ceshi' union select * from student where '1' = '1");
        for(Student ss : s ){
            System.out.println(ss);
        }*/

        //7.测试like模糊查询 方式1，使用${}
/*        List<Student> ss = studentDao.select7("p");
        for(Student s : ss){
            System.out.println(s);
        }*/

        //8.测试like模糊查询 方式2，使用#{}
/*        List<Student> ss = studentDao.select8("%p%");
        for(Student s : ss) {
            System.out.println(s);
        }*/

        //9.测试like模糊查询 方式2，使用#{}
       /* List<Student> ss = studentDao.select9("p");
        for(Student s : ss) {
            System.out.println(s);
        }*/

       //10.测试resultType 返回简单类型String
/*        String s = studentDao.select10("18");
        System.out.println(s);*/

        //11.测试resultType 返回简单类型集合String
/*        List<String> ss = studentDao.select11();
        for(String s : ss){
            System.out.println(s);
        }*/

        //12.测试resultType 返回简单类型int
/*        int count = studentDao.select12();
        System.out.println(count);*/

        //13.测试resultType 返回简单类型domain引用类型

        //14.测试resultType 返回Map
   /*     List<Map<String,Object>> mapList = studentDao.select14();
        for(Map<String,Object> map: mapList){
            Set<String> set = map.keySet();
            for(String s: set){
                System.out.println("key:" + s);
                System.out.println("value:"+ map.get(s));
            }
        }*/

        //16.测试resultMap
        List<Student> ss = studentDao.select16();
        for(Student s : ss){
            System.out.println(s);
        }

    }
}