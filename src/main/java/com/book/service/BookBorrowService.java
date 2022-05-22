package com.book.service;

import com.book.entity.BookBorrowEntity;

import java.util.List;

public interface BookBorrowService {

    List<BookBorrowEntity> getBookBorrowList();

    void returnBook(String id);

    boolean addBorrow(String id, String bid, String sid, String createTime, String returnTime);

    List<BookBorrowEntity> isRepetitionBorrow(String bid);
}
