package com.example.lab3.dao;

import com.example.lab3.dto.PointDto;
import com.example.lab3.entity.PointEntity;
import com.example.lab3.interfaces.BaseDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.hibernate.Session;

import java.util.List;
@Named(value = "pointDao")
@ApplicationScoped
public class PointDao implements BaseDao<PointEntity, PointDto> {


    @Override
    public void create(PointDto pointDto, Session session) {
        session.save(new PointEntity(pointDto));
    }

    @Override
    public List<PointEntity> getAll(Session session) {
        return session.createQuery("from PointEntity", PointEntity.class).getResultList();
    }

    @Override
    public <T> List<PointEntity> getBy(Session session, String field, T data) {
        return session.createQuery("from PointEntity point where point", PointEntity.class).getResultList();
    }
}
