package com.book.servlet.BookManage;

import com.book.entity.BookUserEntity;
import com.book.service.BookBorrowService;
import com.book.service.BookService;
import com.book.service.BookStudentService;
import com.book.service.impl.BookBorrowServiceImpl;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.BookStudentServiceImpl;
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

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {
    private BookService bookService;
    private BookBorrowService borrowService;
    private BookStudentService studentService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
        studentService = new BookStudentServiceImpl();
        borrowService = new BookBorrowServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        BookUserEntity bookUser = (BookUserEntity)req.getSession().getAttribute("user");
        context.setVariable("username",bookUser.getName());
        // 给前端返回书籍列表
        context.setVariable("book_list", bookService.getFilterBookList());
        // 给前端返回学生列表
        context.setVariable("student_list", studentService.getStudentList());

        if (req.getSession().getAttribute("noneReturnTime") != null){
            context.setVariable("noneReturnTime", true);
            req.getSession().removeAttribute("noneReturnTime");
        }
        if (req.getSession().getAttribute("result_book") != null){
            context.setVariable("result_book", true);
            req.getSession().removeAttribute("result_book");
        }


        ThymeleafUtils.process("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String book = req.getParameter("book");
        String student = req.getParameter("student");
        String returnTime = req.getParameter("returnTime");
        String id = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (!returnTime.equals("")) {
            if (borrowService.addBorrow(id, book, student, createTime, returnTime)) {
                resp.sendRedirect("index");
            } else {
                req.getSession().setAttribute("result_book", false);
                this.doGet(req, resp);
            }
        }
        req.getSession().setAttribute("noneReturnTime", false);
        this.doGet(req, resp);
    }
}
