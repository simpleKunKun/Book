package com.book.entity;

import lombok.Data;


@Data
public class BookBorrowEntity {
    private String id;
    private String book_id;
    private String book_name;
    private String student_name;
    private String student_id;
    private int student_class;
    private String createTime;
    private String returnTime;
}
