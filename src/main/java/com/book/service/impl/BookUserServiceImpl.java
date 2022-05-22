package com.book.service.impl;

import com.book.dao.BookUserMapper;
import com.book.entity.BookUserEntity;
import com.book.service.BookUserService;
import com.book.utils.MybatisUtils;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class BookUserServiceImpl implements BookUserService {

    // 判断用户是否存在
    @Override
    public boolean auth(String username, String password, HttpSession httpSession) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookUserMapper bookUserMapper = sqlSession.getMapper(BookUserMapper.class);
            BookUserEntity bookUserEntity = bookUserMapper.getUserOne(username, password);
            if (bookUserEntity == null) return false;
            httpSession.setAttribute("user", bookUserEntity);
            return true;
        }
    }
}
