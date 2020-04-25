package com.liteworm.dao.impl;

import com.liteworm.dao.StudentDao;
import com.liteworm.domain.Student;
import com.liteworm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @ClassName StudentDaoImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 22:49
 * @Version 1.0
 **/
public class StudentDaoImpl implements StudentDao {
    @Override
    public Student getById(String id) {

        SqlSession session = SqlSessionUtil.getSession();
        Student  s = session.selectOne("getById", id);
        return s;
    }

    @Override
    public void save(Student student) {
        SqlSession session = SqlSessionUtil.getSession();
        session.insert("save", student);
    }
}