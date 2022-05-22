package com.book.dao;

import com.book.entity.BookBorrowEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookBorrowMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "book_id"),
            @Result(column = "title", property = "book_name"),
            @Result(column = "name", property = "student_name"),
            @Result(column = "sid", property = "student_id"),
            @Result(column = "class", property = "student_class"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "returnTime", property = "returnTime")
    })
    @Select("select * from book_borrow bb " +
            "join book_student s on bb.sid = s.sid " +
            "join book_information b on bb.bid = b.bid")
    List<BookBorrowEntity> getBookBorrowList();

    @Delete("delete from book_borrow where id = #{id}")
    void returnBook(@Param("id") String id);

    @Insert("insert into book_borrow(id,bid,sid,createTime,returnTime) values(#{id},#{bid},#{sid},#{createTime},#{returnTime})")
    void addBookBorrow(@Param("id") String id,@Param("bid") String bid,@Param("sid") String sid,@Param("createTime") String createTime,@Param("returnTime") String returnTime);

    @Select("select * from book_borrow where bid = #{bid}")
    List<BookBorrowEntity> isRepetitionBorrow(@Param("bid") String bid);
}
