package com.example.lab2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getServletContext().setAttribute("leadTime", System.nanoTime());
        if (req.getQueryString() != null) {
            getServletContext().getRequestDispatcher("/hit").forward(req, res);
        } else
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);

    }
}
