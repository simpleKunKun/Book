package com.book.servlet.BookManage;

import com.book.entity.BookUserEntity;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/update-book")
public class UpdateBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        Context context = new Context();
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");
        context.setVariable("username",bookUser.getName());
        context.setVariable("bookInformation",bookService.getBook(bid));
        if (req.getSession().getAttribute("unsuccessful_book") != null) {
            context.setVariable("unsuccessful_book", true);
            req.getSession().removeAttribute("unsuccessful_book");
        }
        ThymeleafUtils.process("update-book.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        if (!title.equals("") && !desc.equals("") && !price.equals("")) {
            bookService.updateBook(bid, title, desc, price);
            resp.sendRedirect("books");
        }
        req.getSession().setAttribute("unsuccessful_book", false);
        this.doGet(req, resp);
    }
}
