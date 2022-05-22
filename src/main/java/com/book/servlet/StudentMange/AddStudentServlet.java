package com.book.servlet.StudentMange;

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

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    private BookStudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new BookStudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");
        context.setVariable("username",bookUser.getName());
        if (req.getSession().getAttribute("unsuccessful_student") != null) {
            context.setVariable("unsuccessful_student", true);
            req.getSession().removeAttribute("unsuccessful_student");
        }
        ThymeleafUtils.process("add-student.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("S_name");
        String sex = req.getParameter("S_sex");
        String age = req.getParameter("S_age");
        int c_class = Integer.parseInt(req.getParameter("S_class"));
        if (!name.equals("") && !age.equals("")) {
            studentService.addStudent(name,sex,Integer.parseInt(age),c_class);
            resp.sendRedirect("students");
        }
        req.getSession().setAttribute("unsuccessful_student", false);
        this.doGet(req, resp);
    }
}
