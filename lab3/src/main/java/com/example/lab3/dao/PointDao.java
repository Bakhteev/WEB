package com.example.lab3.dao;

import com.example.lab3.dto.PointDto;
import com.example.lab3.entity.PointEntity;
import com.example.lab3.entity.UserEntity;
import com.example.lab3.interfaces.BaseDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;
@Named(value = "pointDao")
@ApplicationScoped
public class PointDao {


    public void create(PointDto pointDto, UserEntity user, Session session) {
        session.save(new PointEntity(pointDto, user));
    }

    public List<PointEntity> getAll(Session session, UserEntity user) {
        return session.createQuery("from PointEntity point where point.user = :userId", PointEntity.class)
                .setParameter("userId", user)
                .getResultList();
    }
}
