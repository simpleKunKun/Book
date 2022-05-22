package com.book.service;

import com.book.entity.BookEntity;

import java.util.List;
import java.util.Map;

public interface BookService {

    // 查询书籍列表
    Map<BookEntity,Boolean> getBookList();

    // 查询未被借出的书籍
    List<BookEntity> getFilterBookList();

    // 添加一条书籍
    boolean addBook(String bid, String title, String desc, String price);

    // 删除书籍
    void removeBook(String bid);

    // 根据id获取书籍信息
    BookEntity getBook(String bid);

    // 修改书籍信息
    void updateBook(String bid, String title, String desc, String price);
}
