package com.book.dao;

import com.book.entity.BookStudentEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookStudentMapper {
    @Results(
            @Result(column = "class", property = "c_class")
    )
    @Select("select * from book_student")
    List<BookStudentEntity> getStudentList();

    @Results(
            @Result(column = "class", property = "c_class")
    )
    @Select("select * from book_student where sid = #{sid}")
    BookStudentEntity getStudent(String sid);

    @Insert("insert into book_student values(#{sid},#{name},#{sex},#{age},#{class})")
    void addStudent(@Param("sid") String sid, @Param("name") String name, @Param("sex") String sex, @Param("age") int age, @Param("class") int c_class);

    @Delete("delete from book_student where sid = #{sid}")
    void removeStudent(String sid);

    @Results(
            @Result(column = "class", property = "c_class")
    )
    @Update("update book_student set `name`=#{name},`sex`=#{sex},`age`=#{age},`class`=#{class} where sid=#{sid}")
    boolean updateStudent(@Param("sid") String sid, @Param("name") String name, @Param("sex") String sex, @Param("age") int age, @Param("class") int c_class);
}
