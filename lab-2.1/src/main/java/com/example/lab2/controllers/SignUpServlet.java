package com.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title","Sign Up");
        req.setAttribute("formTo", "/signup");
        req.setAttribute("linkTo","/login");
        req.setAttribute("linkText","log in");

        getServletContext().getRequestDispatcher("/auth.jsp").forward(req, res);
    }
}
