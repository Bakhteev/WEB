package com.example.lab3.dao;

import com.example.lab3.dto.PointDto;
import com.example.lab3.entity.PointEntity;
import com.example.lab3.interfaces.BaseDao;
import org.hibernate.Session;

import java.util.List;

public class PointDao implements BaseDao<PointEntity, PointDto> {


    @Override
    public void create(PointDto pointDto, Session session) {
        session.save(new PointEntity(pointDto));
    }

    @Override
    public List<PointEntity> getAll(Session session) {
        return session.createQuery("from PointEntity", PointEntity.class).getResultList();
    }
}
