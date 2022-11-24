package com.example.lab3.beans;


import com.example.lab3.dto.PointDto;
import com.example.lab3.services.PointService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Named(value = "pointBeanWorker")
@ApplicationScoped
@Data
public class PointBeanWorker {

    private LinkedList<PointDto> pointsState = new LinkedList<>();
    @Inject
    private PointBean pointBean;

    private PointService pointService;

    public PointBeanWorker() {
        this.pointService = new PointService();
    }

    public void addPoint() {
        PointDto point = pointBean.createPoint();
        pointsState.add(point);
        pointService.addPoint(point);
    }

    public List<PointDto> getPointsState() {
        if (pointsState.size() > 0) {
            return this.pointsState;
        }
        pointsState = this.pointService.getAllPoints();
        return pointsState;
    }
}
