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

@WebServlet("/update-student")
public class UpdateStudentServlet extends HttpServlet {
    BookStudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new BookStudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        BookUserEntity userEntity = (BookUserEntity) req.getSession().getAttribute("user");
        context.setVariable("username",userEntity.getUsername());

        if (req.getSession().getAttribute("unsuccessful_student") != null) {
            context.setVariable("unsuccessful_student", true);
            req.getSession().removeAttribute("unsuccessful_student");
        }
        context.setVariable("studentInformation",studentService.getStudent(req.getParameter("sid")));
        ThymeleafUtils.process("update-student.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String S_name = req.getParameter("S_name");
        String S_sex = req.getParameter("S_sex");
        String S_age = req.getParameter("S_age");
        int c_class = Integer.parseInt(req.getParameter("S_class"));
        if (!S_name.equals("") && !S_age.equals("")) {
            studentService.updateStudent(sid,S_name,S_sex,Integer.parseInt(S_age),c_class);
            resp.sendRedirect("students");
        }
        req.getSession().setAttribute("unsuccessful_student", false);
        this.doGet(req, resp);
    }
}
