package com.example.lab2.controllers;

import com.example.lab2.models.Point;
import com.example.lab2.services.AreaCheckService;
import com.example.lab2.state.StateBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "hit", value = "/hit")
public class AreaCheckServlet extends HttpServlet {

    AreaCheckService areaCheckService;
    StateBean stateBean;

    @Override
    public void init() {
        stateBean = new StateBean();
        areaCheckService = new AreaCheckService();
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
            float executionTime = (float) (System.nanoTime() - startTime)/1_000_000;

            point = new Point(x, y, r, hitted, date, executionTime);
        } catch (InvalidParameterException e) {
            res.setStatus(400);
            error = e.getMessage();
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }
        req.setAttribute("point", point);
        getServletContext().getRequestDispatcher("/table.jsp").forward(req, res);
    }
}
