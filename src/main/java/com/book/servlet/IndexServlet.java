package com.book.servlet;

import com.book.entity.BookUserEntity;
import com.book.service.BookBorrowService;
import com.book.service.CountService;
import com.book.service.impl.BookBorrowServiceImpl;
import com.book.service.impl.CountServiceImpl;
import com.book.utils.ThymeleafUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private BookBorrowService borrowService;
    private CountService countService;

    @Override
    public void init() throws ServletException {
        borrowService = new BookBorrowServiceImpl();
        countService = new CountServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        // 获取当前用户
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");

        // 给返回前端的参数
        context.setVariable("username",bookUser.getName()); // 返回用户名
        context.setVariable("borrowList",borrowService.getBookBorrowList());    // 返回借阅列表
        context.setVariable("bookNumber",countService.getBookCount());
        context.setVariable("studentNumber",countService.getStudentCount());
        context.setVariable("borrowNumber",countService.getBorrowCount());


        ThymeleafUtils.process("index.html", context, resp.getWriter());
    }
}
