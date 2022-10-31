package com.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title","Log in");
        req.setAttribute("formTo", "/login");
        req.setAttribute("linkTo","/signup");
        req.setAttribute("linkText","Sign up");

        getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
    }
}
