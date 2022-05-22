package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.entity.BookEntity;

import com.book.service.BookService;
import com.book.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    @Override
    public Map<BookEntity,Boolean> getBookList() {
        Set<String> set = new HashSet<>();
        this.getFilterBookList().forEach(filterBook -> set.add(filterBook.getBid()));
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            Map<BookEntity,Boolean> bookListMap = new LinkedHashMap<>();
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.getBookList().forEach(bookEntity -> bookListMap.put(bookEntity,set.contains(bookEntity.getBid())));
            return bookListMap;
        }
    }


    public List<BookEntity> getFilterBookList() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            // 将以被借阅的书id取出
            Set<String> set = new HashSet<>();
            new BookBorrowServiceImpl().getBookBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
            // 过滤掉被借了的书籍然后返回当前可被借阅的书籍列表
            return mapper.getBookList()
                    .stream()
                    .filter(bookEntity -> !set.contains(bookEntity.getBid()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public boolean addBook(String bid, String title, String desc, String price) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            Double d_price = Double.parseDouble(price);
            return mapper.addBook(bid, title, desc, d_price);
        }
    }

    @Override
    public void removeBook(String bid) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.removeBook(bid);
        }
    }

    @Override
    public BookEntity getBook(String bid) {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBook(bid);
        }
    }

    @Override
    public void updateBook(String bid, String title, String desc, String price) {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            Double d_price = Double.parseDouble(price);
            mapper.updateBook(bid, title, desc, d_price);
        }
    }
}
