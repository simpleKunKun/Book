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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/add-book")
public class AddBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");
        context.setVariable("username",bookUser.getName());
        if (req.getSession().getAttribute("unsuccessful_book") != null) {
            context.setVariable("unsuccessful_book", true);
            req.getSession().removeAttribute("unsuccessful_book");
        }
        ThymeleafUtils.process("add-book.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if (!title.equals("") && !desc.equals("") && !price.equals("")) {
            bookService.addBook(id, title, desc, price);
            resp.sendRedirect("books");
        }
        req.getSession().setAttribute("unsuccessful_book", false);
        this.doGet(req, resp);
    }
}
