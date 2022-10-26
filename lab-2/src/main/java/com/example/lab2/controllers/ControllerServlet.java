package com.example.lab2.controllers;

import com.example.lab2.enums.CommandTypes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            getServletContext().getRequestDispatcher("/hit").forward(req, res);
        } else
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);

    }
}
