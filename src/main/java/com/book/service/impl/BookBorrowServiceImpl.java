package com.book.service.impl;

import com.book.dao.BookBorrowMapper;
import com.book.entity.BookBorrowEntity;
import com.book.service.BookBorrowService;
import com.book.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class BookBorrowServiceImpl implements BookBorrowService {
    @Override
    public List<BookBorrowEntity> getBookBorrowList() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookBorrowMapper borrowMapper = sqlSession.getMapper(BookBorrowMapper.class);
            // 返回借阅书籍列表
            return borrowMapper.getBookBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            // 归还书籍
            sqlSession.getMapper(BookBorrowMapper.class).returnBook(id);
        }
    }

    @Override
    public boolean addBorrow(String id, String bid, String sid, String createTime, String returnTime) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            List<BookBorrowEntity> repetitionBorrow = isRepetitionBorrow(bid);
            // 判断是否已被借出
            if (repetitionBorrow.isEmpty()) {
                // 添加借阅记录
                sqlSession.getMapper(BookBorrowMapper.class).addBookBorrow(id, bid, sid, createTime, returnTime);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BookBorrowEntity> isRepetitionBorrow(String bid) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            // 返回已存在的借阅记录
            return sqlSession.getMapper(BookBorrowMapper.class).isRepetitionBorrow(bid);
        }
    }
}
