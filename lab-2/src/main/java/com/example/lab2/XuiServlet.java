package com.example.lab2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "xui", value = "/xui")
public class XuiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        Point point = new Point(11, 11, 11);
//        Point point2 = new Point(110, 110, 110);
//        Point[] arr = {point, point2, point2, point2, point2, point2, point2};
//        request.setAttribute("points", arr);
//        try {
//            getServletConfig().getServletContext().getRequestDispatcher("/xui.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void destroy() {
    }
}
