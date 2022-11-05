package com.example.lab2.filters;

import com.example.lab2.services.JWTService;
import com.example.lab2.state.UserState;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;

@WebFilter(urlPatterns = {"/", "/history",})
public class AuthFilter extends HttpFilter {

    JWTService jwtService;

    @Override
    public void init() throws ServletException {
        UserState userState = (UserState) getServletContext().getAttribute("userState");
        if (userState == null) {
            userState = new UserState();
            getServletContext().setAttribute("userState", userState);
        }
        jwtService = new JWTService(userState);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String authorization = req.getHeader("Authorization");
//        if(authorization == null || !(authorization.matches("Bearer .+"))){
//            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//        String token = authorization.replaceAll("Bearer", "").trim();
        String token = "";
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        if (token.length() == 0) {
            res.sendRedirect(req.getContextPath() + "/login");
            getServletContext().getRequestDispatcher("/login").forward(req,res);
            return;
        }
        if (jwtService.authenticate(token)) {
            chain.doFilter(req, res);
            return;
        }
        res.sendRedirect(req.getContextPath() + "/login");
        getServletContext().getRequestDispatcher("/login").forward(req,res);
    }
}
