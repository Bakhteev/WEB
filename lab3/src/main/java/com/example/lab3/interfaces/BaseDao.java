package com.example.lab3.interfaces;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao<E, D> {
    void create(D obj, Session session);

    List<E> getAll(Session session);

    <T> List<E> getBy(Session session, String field, T data);
}
