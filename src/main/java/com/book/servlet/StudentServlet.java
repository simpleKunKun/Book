package com.book.servlet;

import com.book.entity.BookUserEntity;
import com.book.service.BookStudentService;
import com.book.service.impl.BookStudentServiceImpl;
import com.book.utils.ThymeleafUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    BookStudentService studentsService;

    @Override
    public void init() throws ServletException {
        studentsService = new BookStudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        // 获取当前用户
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");
        context.setVariable("username",bookUser.getName());
        context.setVariable("StudentList",studentsService.getStudentList());
        ThymeleafUtils.process("students.html",context,resp.getWriter());
    }
}
