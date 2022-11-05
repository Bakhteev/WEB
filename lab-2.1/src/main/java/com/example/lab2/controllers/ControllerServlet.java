package com.example.lab2.controllers;

import com.example.lab2.state.UserState;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    UserState userState;

    @Override
    public void init() throws ServletException {
        userState = (UserState) getServletContext().getAttribute("userState");
        if (userState == null) {
            userState = new UserState();
            getServletContext().setAttribute("userState", userState);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String token = "";
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }

        if (token.length() == 0) {
            res.sendRedirect("login");
//            return;
        } else {
            req.getServletContext().setAttribute("leadTime", System.nanoTime());
            if (req.getQueryString() != null) {
                getServletContext().getRequestDispatcher("/hit").forward(req, res);
            } else {
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
            }
        }

    }
}
