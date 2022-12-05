package com.example.lab3.filters;

import com.example.lab3.entity.UserEntity;
import com.example.lab3.services.AuthService;
import com.example.lab3.services.JWTService;
import com.example.lab3.state.UserState;
import com.example.lab3.utils.CookieParser;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.inject.Inject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"*.xhtml"})
public class AuthFilter extends HttpFilter {


    @Inject
    JWTService jwtService;

    @Inject
    AuthService authService;

    @Inject
    UserState userState;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Cookie cookie = CookieParser.getCookie(req, "token");
        String token = cookie == null ? "" : cookie.getValue();

        if (token.length() == 0) {
            res.setStatus(301);
            res.sendRedirect("/login");
            getServletContext().getRequestDispatcher("/login").forward(req, res);
            return;
        }
        try {
            if (jwtService.authenticate(token)) {
                String email = (String) jwtService.getClaims(token).getBody().get("email");
                UserEntity user = authService.getUserByEmail(email);
                if (user != null) {
                    userState.setUser(user);
                    chain.doFilter(req, res);
                    return;
                }
            }
        } catch (ExpiredJwtException e) {
            res.setStatus(301);
            res.sendRedirect("/login");
        }
        res.setStatus(301);
        res.sendRedirect("/login");
    }
}
