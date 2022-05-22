package com.book.dao;

import com.book.entity.BookEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Select("select * from book_information")
    List<BookEntity> getBookList();

    @Insert("insert into book_information values(#{bid},#{title},#{desc},#{price})")
    boolean addBook(@Param("bid") String bid, @Param("title") String title, @Param("desc") String desc, @Param("price") Double price);

    @Delete("delete from book_information where bid = #{bid}")
    void removeBook(@Param("bid") String bid);

    @Select("select * from book_information where bid = #{bid}")
    BookEntity getBook(String bid);

    @Update("update book_information set title=#{title},`desc`=#{desc},price=#{price} where bid=#{bid}")
    boolean updateBook(@Param("bid") String bid, @Param("title") String title, @Param("desc") String desc, @Param("price") Double price);

}
