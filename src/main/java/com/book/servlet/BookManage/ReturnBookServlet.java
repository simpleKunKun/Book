package com.book.servlet.BookManage;

import com.book.service.BookBorrowService;
import com.book.service.impl.BookBorrowServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 归还书籍接口
@WebServlet("/return-book")
public class ReturnBookServlet extends HttpServlet {
    private BookBorrowService borrowService;

    @Override
    public void init() throws ServletException {
        borrowService = new BookBorrowServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取书籍id
        String id = req.getParameter("id");
        borrowService.returnBook(id);
        resp.sendRedirect("index");
    }
}
