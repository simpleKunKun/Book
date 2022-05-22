package com.book.servlet.auth;

import com.book.service.BookUserService;
import com.book.service.impl.BookUserServiceImpl;
import com.book.utils.ThymeleafUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.thymeleaf.context.Context;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    BookUserService bookUserService;

    @Override
    public void init() throws ServletException {
        bookUserService = new BookUserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Context context = new Context();

        // 判断用户是否已登录，已登录直接跳转进入主页
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }

        // 通过cookie判断是否记住密码，自动填充账号密码
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) context.setVariable("cookie_username", cookie.getValue());
                if (cookie.getName().equals("password")) context.setVariable("cookie_password", cookie.getValue());
            }
        }

        // 用户信息错误返回错误信息
        if (req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", req.getSession().getAttribute("login-failure"));
            req.getSession().removeAttribute("login-failure");
        }


        ThymeleafUtils.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!username.equals("") && !password.equals("")) {
            // 判断是否存在用户
            if (bookUserService.auth(username, password, req.getSession())) {
                // 判断是否选择了记住密码
                if (req.getParameter("remember-me") != null) {
                    Cookie cookie_name = new Cookie("username", username);
                    cookie_name.setMaxAge(60 * 60 * 24);
                    Cookie cookie_pwd = new Cookie("password", password);
                    cookie_pwd.setMaxAge(60 * 60 * 24);
                    resp.addCookie(cookie_name);
                    resp.addCookie(cookie_pwd);
                }

                // 登录成功跳转进入index页
                resp.sendRedirect("index");
            } else {
                // 当登录失败时，页面显示提示信息
                req.getSession().setAttribute("login-failure", 2);
                this.doGet(req, resp);
            }
        } else {
            // 当登录失败时，页面显示提示信息
            req.getSession().setAttribute("login-failure", 1);
            this.doGet(req, resp);
        }


    }
}
