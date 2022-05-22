package com.book.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface CountMapper {
    @ResultType(value = Integer.class)
    @Select("select count(*) as bookNumber from book_information")
    int getBookCount();

    @ResultType(value = Integer.class)
    @Select("select count(*) as studentNumber from book_student")
    int getStudentCount();

    @ResultType(value = Integer.class)
    @Select("select count(*) as borrowNumber from book_borrow")
    int getBorrowCount();
}
