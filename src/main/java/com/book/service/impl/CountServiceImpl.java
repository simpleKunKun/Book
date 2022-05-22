package com.book.service.impl;

import com.book.dao.CountMapper;
import com.book.service.CountService;
import com.book.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class CountServiceImpl implements CountService {
    @Override
    public int getBookCount() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            return sqlSession.getMapper(CountMapper.class).getBookCount();
        }
    }

    @Override
    public int getStudentCount() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            return sqlSession.getMapper(CountMapper.class).getStudentCount();
        }
    }

    @Override
    public int getBorrowCount() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            return sqlSession.getMapper(CountMapper.class).getBorrowCount();
        }
    }
}
