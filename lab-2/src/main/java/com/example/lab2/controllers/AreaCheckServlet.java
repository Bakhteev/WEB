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

@WebServlet(name = "hit", value = "/hit")
public class AreaCheckServlet extends HttpServlet {

    AreaCheckService areaCheckService;
    StateBean stateBean = new StateBean();

    @Override
    public void init() throws ServletException {
        areaCheckService = new AreaCheckService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long startTime = System.nanoTime();
        int x = Integer.parseInt(req.getParameter("x"));
        float y = Float.parseFloat(req.getParameter("y"));
        float r = Float.parseFloat(req.getParameter("r"));
        String error = "";
        try {
            areaCheckService.validate(x, y, r);
            boolean hitted = areaCheckService.checkHit(x, y, r);
            Point point = new Point(x, y, r, hitted);
//            Point[] arr = {point};
            this.stateBean.add(point);
        } catch (InvalidParameterException e) {
            res.setStatus(400);
            error = e.getMessage();
        } finally {
            req.setAttribute("points", this.stateBean);
            req.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/xui.jsp").forward(req, res);
        }
    }
}
