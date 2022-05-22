package com.book.service;

import jakarta.servlet.http.HttpSession;

public interface BookUserService {

    boolean auth(String username, String password, HttpSession httpSession);
}
