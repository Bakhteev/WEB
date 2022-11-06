package com.example.lab2.controllers;

import com.example.lab2.models.User;
import com.example.lab2.services.AuthService;
import com.example.lab2.services.JWTService;
import com.example.lab2.state.HitState;
import com.example.lab2.state.UserState;
import com.example.lab2.utils.PasswordHash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
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
        req.setAttribute("title", "Log in");
        req.setAttribute("formTo", "/login");
        req.setAttribute("linkTo", "/signup");
        req.setAttribute("linkText", "Sign up");

        getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!authService.validateCredentials(email, password)) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("fuck u");
            return;
        }
        User user = userState.getUserByEmail(email);
        if(user == null){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Wrong email or password");
            res.sendRedirect("/signup");
            getServletContext().getRequestDispatcher("/auth.jsp").forward(req,res);
            return;
        }
        if(!(user.getPasswordHash().equals(PasswordHash.generateHashedPassword(password)))){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("Wrong password or email");
            return;
        }
        res.addCookie(new Cookie("token", jwtService.createJwtToken(user)));
        getServletContext().setAttribute("userState", userState);
        getServletContext().setAttribute("hitState", hitState);
        res.sendRedirect("/");
//        getServletContext().getRequestDispatcher("/index.jsp").forward(req,res);
    }
}
