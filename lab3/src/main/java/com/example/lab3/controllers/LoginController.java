package com.example.lab3.controllers;


import com.example.lab3.dto.UserDto;
import com.example.lab3.services.AuthService;
import com.example.lab3.validators.ValidateCredentials;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet()
public class LoginController extends HttpServlet {
    @Inject
    AuthService authService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", "Log in");
        req.setAttribute("formTo", "/login");
        req.setAttribute("linkTo", "/signup");
        req.setAttribute("linkText", "Sign up");

        getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDto userDto = new Gson().fromJson(req.getReader(), UserDto.class);
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        if (!ValidateCredentials.validateCredentials(email, password)) {
            res.setStatus(400);
            res.getWriter().write("Credentials is incorrect");
            return;
        }
        User user = userState.getUserByEmail(email);
        if (user == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Wrong email or password");
            res.sendRedirect("/signup");
            getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
            return;
        }
        if (!(user.getPasswordHash().equals(PasswordHash.generateHashedPassword(password)))) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("Wrong password or email");
            return;
        }
        Cookie cookie = new Cookie("token", jwtService.createJwtToken(user));
        cookie.setMaxAge(1000 * 60 * 60 * 24);
        res.addCookie(cookie);
        getServletContext().setAttribute("userState", userState);
        getServletContext().setAttribute("hitState", hitState);
        res.sendRedirect("/");
    }
}
