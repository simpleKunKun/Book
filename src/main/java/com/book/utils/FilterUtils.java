package com.book.utils;

import com.book.entity.BookUserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class FilterUtils extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 获取当前请求的url
        String url = req.getRequestURL().toString();

        // 判断若是请求静态资源相关的请求直接放行
        if (!url.contains("/static/") && !url.endsWith("login")){
            HttpSession session = req.getSession();
            BookUserEntity userEntity = (BookUserEntity)session.getAttribute("user");

            // 判断当前是否已登录
            if (userEntity == null){
                // 重定向到登录页
                res.sendRedirect("login");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
