package com.example.lab2.controllers;

import com.example.lab2.models.Point;
import com.example.lab2.services.JWTService;
import com.example.lab2.state.HitState;
import com.example.lab2.utils.CookieParser;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    LinkedList<Point> currentUsersList;
    Gson gson;
    JWTService jwtService;
    HitState hitState;

    @Override
    public void init() throws ServletException {
        gson = new Gson();
        hitState = (HitState) getServletContext().getAttribute("hitState");
        jwtService = (JWTService) getServletContext().getAttribute("jwtService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String token = Objects.requireNonNull(CookieParser.getCookie(req, "token")).getValue();
        int userId = jwtService.getUserFromToken(token).getId();
        currentUsersList = hitState.getList(userId);
        res.setStatus(200);
        res.setContentType("application/json");
        res.getWriter().write(gson.toJson(currentUsersList));
    }
}
