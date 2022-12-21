package com.example.lab3.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieParser {
    static public Cookie getCookie(HttpServletRequest req, String cookieName) {
        if(req.getCookies() == null) return null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }
}