package com.book.servlet.StudentMange;

import com.book.service.BookStudentService;
import com.book.service.impl.BookStudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/remove-student")
public class RemoveStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookStudentService bookStudentService = new BookStudentServiceImpl();
        bookStudentService.removeStudent(req.getParameter("sid"));
        resp.sendRedirect("students");
    }
}
