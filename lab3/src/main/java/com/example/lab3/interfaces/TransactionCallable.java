package com.example.lab3.interfaces;

import org.hibernate.Session;

public interface TransactionCallable<T> {
    public abstract T execute(Session session);
}
