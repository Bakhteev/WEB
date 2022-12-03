package com.example.lab3.services;

import com.example.lab3.dao.PointDao;
import com.example.lab3.dto.PointDto;
import com.example.lab3.entity.PointEntity;
import com.example.lab3.interfaces.TransactionCallable;
import com.example.lab3.utils.TransactionManager;
import jakarta.inject.Inject;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public class PointService {

    private final PointDao pointDao;

    public PointService(PointDao pointDao) {
        this.pointDao = pointDao;
    }

    public void addPoint(PointDto pointDto) {
        TransactionManager.doInTransaction(session -> {
            long leadTime = pointDto.getLeadTime();
            pointDto.setLeadTime((System.nanoTime() - leadTime) / 1000);
            pointDao.create(pointDto, session);
            return pointDto;
        });
    }

    public LinkedList<PointDto> getAllPoints() {
        LinkedList<PointDto> pointDtos = new LinkedList<>();
        List<PointEntity> points = TransactionManager.doInTransaction(session ->
                pointDao.getAll(session)
        );
        points.forEach(point ->
                pointDtos.add(
                        new PointDto(
                                point.getXValue(),
                                point.getYValue(),
                                point.getRValue(),
                                point.getHit(),
                                point.getDate(),
                                point.getLeadTime()
                        )
                )
        );
        return pointDtos;
    }
}
