package com.example.lab3.controllers;

import com.example.lab3.dao.UserDao;
import com.example.lab3.dto.UserDto;
import com.example.lab3.entity.UserEntity;
import com.example.lab3.services.AuthService;
import com.example.lab3.services.JWTService;
import com.example.lab3.state.UserState;
import com.example.lab3.utils.PasswordHash;
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

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    @Inject
    AuthService authService;
    @Inject
    JWTService jwtService;

    @Inject
    UserState userState;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", "Sign Up");
        req.setAttribute("formTo", "/signup");
        req.setAttribute("linkTo", "/login");
        req.setAttribute("linkText", "log in");

        getServletContext().getRequestDispatcher("/start.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDto userDto = new Gson().fromJson(req.getReader(), UserDto.class);
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        if (!ValidateCredentials.validateCredentials(email, password)) {
            res.setStatus(400);
            res.getWriter().print("Credentials are incorrect");
            return;
        }
        if (authService.getUserByEmail(email) != null) {
            res.setStatus(400);
            res.getWriter().write("This email has already registered");
            return;
        }
        UserDto user = new UserDto(email, PasswordHash.generateHashedPassword(password));
        authService.addUser(user);
        UserEntity userEntity =  authService.getUserByEmail(email);
        Cookie cookie = new Cookie("token", jwtService.createJwtToken(userEntity));
        cookie.setMaxAge(1000 * 60 * 60 * 24);
        res.addCookie(cookie);
        userState.setUser(userEntity);
        res.sendRedirect("/");
    }
}
