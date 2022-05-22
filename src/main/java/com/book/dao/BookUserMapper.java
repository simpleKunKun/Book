package com.book.dao;

import com.book.entity.BookUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BookUserMapper {
    @Select("select * from book_user where username = #{username} and password = #{password}")
    BookUserEntity getUserOne(@Param("username") String username, @Param("password") String password);
}
