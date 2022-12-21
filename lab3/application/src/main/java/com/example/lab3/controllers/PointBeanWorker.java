package com.example.lab3.controllers;


import com.example.lab3.dao.PointDao;
import com.example.lab3.dto.PointDto;
import com.example.lab3.services.JWTService;
import com.example.lab3.services.PointService;
import com.example.lab3.state.UserState;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named(value = "pointBeanWorker")
@SessionScoped
@Data
public class PointBeanWorker implements Serializable {

    private static final long serialVersionUID = -6677745145634363295L;

    private LinkedList<PointDto> pointsState = new LinkedList<>();
    @Inject
    private PointBean pointBean;

    @Inject
    UserState userState;
    private PointService pointService;

    public PointBeanWorker() {
        this.pointService = new PointService(new PointDao());
    }

    public void addPoint() {
        try {
            PointDto point = pointBean.createPoint();
            pointsState.add(point);
            pointService.addPoint(point, userState.getUser());
        } catch (Exception ignored) {
        }
    }

    public List<PointDto> getPointsState() {
        if (pointsState.size() > 0) {
            return this.pointsState;
        }
        pointsState = this.pointService.getAllPoints(userState.getUser());
        return pointsState;
    }
}
