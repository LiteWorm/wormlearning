package com.liteworm.service;

import com.liteworm.domain.Student;

/**
 * @ClassName StudentService
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 20:16
 * @Version 1.0
 **/
public interface StudentService {
    public  Student queryStudentById(String id);
    public  void save(Student student);
}
