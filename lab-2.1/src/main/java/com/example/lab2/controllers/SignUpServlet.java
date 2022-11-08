package com.example.lab2.controllers;

import com.example.lab2.dto.UserDto;
import com.example.lab2.models.User;
import com.example.lab2.services.AuthService;
import com.example.lab2.services.JWTService;
import com.example.lab2.state.HitState;
import com.example.lab2.state.UserState;
import com.example.lab2.utils.PasswordHash;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    AuthService authService;
    UserState userState;
    JWTService jwtService;
    HitState hitState;

    @Override
    public void init() throws ServletException {
        userState = (UserState) getServletContext().getAttribute("userState");
        hitState = (HitState) getServletContext().getAttribute("hitState");
        if (userState == null) {
            userState = new UserState();
            getServletContext().setAttribute("userState", userState);
        }
        if (hitState == null) {
            hitState = new HitState();
            getServletContext().setAttribute("hitState", hitState);
        }
        authService = new AuthService();
        jwtService = new JWTService(userState);
        getServletContext().setAttribute("jwtService", jwtService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", "Sign Up");
        req.setAttribute("formTo", "/signup");
        req.setAttribute("linkTo", "/login");
        req.setAttribute("linkText", "log in");

        getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDto userDto = new Gson().fromJson(req.getReader(), UserDto.class);
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        if (!authService.validateCredentials(email, password)) {
            res.setStatus(400);
            res.getWriter().print("Credentials are incorrect");
            return;
        }
        if (userState.getUserByEmail(email) != null) {
            res.setStatus(400);
            res.getWriter().write("This email has already registered");
            return;
        }
        User user = new User(email, PasswordHash.generateHashedPassword(password));
        userState.addUser(user);
        hitState.createUsersList(user.getId());
        Cookie cookie = new Cookie("token", jwtService.createJwtToken(user));
        cookie.setMaxAge(1000 * 60 * 60 * 24);
        res.addCookie(cookie);
        getServletContext().setAttribute("userState", userState);
        getServletContext().setAttribute("hitState", hitState);
        res.sendRedirect("/");
    }
}
