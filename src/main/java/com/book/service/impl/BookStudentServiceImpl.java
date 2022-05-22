package com.book.service.impl;

import com.book.dao.BookStudentMapper;
import com.book.entity.BookStudentEntity;
import com.book.service.BookStudentService;
import com.book.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookStudentServiceImpl implements BookStudentService {
    @Override
    public List<BookStudentEntity> getStudentList() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            // 返回学生列表
            return sqlSession.getMapper(BookStudentMapper.class).getStudentList();
        }
    }

    @Override
    public BookStudentEntity getStudent(String sid) {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            // 返回学生列表
            return sqlSession.getMapper(BookStudentMapper.class).getStudent(sid);
        }
    }

    @Override
    public void addStudent(String name, String sex, int age, int c_class) {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            String sid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            sqlSession.getMapper(BookStudentMapper.class).addStudent(sid, name, sex, age, c_class);
        }
    }

    @Override
    public void removeStudent(String sid) {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            sqlSession.getMapper(BookStudentMapper.class).removeStudent(sid);
        }
    }

    @Override
    public boolean updateStudent(String sid, String name, String sex, int age, int c_class) {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            BookStudentMapper mapper = sqlSession.getMapper(BookStudentMapper.class);
            return mapper.updateStudent(sid, name, sex, age, c_class);
        }
    }
}
