package com.book.service;

import com.book.entity.BookStudentEntity;

import java.util.List;

public interface BookStudentService {
    // 学生列表
    List<BookStudentEntity> getStudentList();

    // 查询某个学生
    BookStudentEntity getStudent(String sid);

    // 添加学生
    void addStudent(String name, String sex, int age, int c_class);

    // 删除学生
    void removeStudent(String sid);

    // 修改学生信息
    boolean updateStudent(String sid,String name,String sex,int age,int c_class);
}
