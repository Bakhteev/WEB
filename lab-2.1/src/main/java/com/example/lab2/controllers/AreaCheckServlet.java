package com.example.lab2.controllers;

import com.example.lab2.models.Point;
import com.example.lab2.services.AreaCheckService;
//import com.example.lab2.state.StateBean;
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
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

@WebServlet(name = "hit", value = "/hit")
public class AreaCheckServlet extends HttpServlet {

    AreaCheckService areaCheckService;
    Gson gson;
    HitState hitState;
    JWTService jwtService;

    @Override
    public void init() {
        hitState = (HitState) getServletContext().getAttribute("hitState");
        areaCheckService = new AreaCheckService();
        gson = new Gson();
        jwtService = (JWTService) getServletContext().getAttribute("jwtService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String error = "";
        Point point = null;
        int x;
        float y;
        float r;
        try {

            x = Integer.parseInt(req.getParameter("x"));
            y = Float.parseFloat(req.getParameter("y"));
            r = Float.parseFloat(req.getParameter("r"));
        } catch (NumberFormatException e) {
            res.setStatus(400);
            error = "Wrong number format";
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        try {
            areaCheckService.validate(x, y, r);
            boolean hitted = areaCheckService.checkHit(x, y, r);
            String pattern = "dd.MM.yyyy hh:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());

            long startTime = (long) getServletContext().getAttribute("leadTime");
            float executionTime = (float) (System.nanoTime() - startTime) / 1_000_000;

            point = new Point(x, y, r, hitted, date, executionTime);
        } catch (InvalidParameterException e) {
            res.setStatus(400);
            error = e.getMessage();
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }
        String token = Objects.requireNonNull(CookieParser.getCookie(req, "token")).getValue();
        int userId = jwtService.getUserFromToken(token).getId();
        hitState.add(userId, point);
        res.getWriter().write(gson.toJson(point));
        getServletContext().setAttribute("hitState", hitState);
    }
}
