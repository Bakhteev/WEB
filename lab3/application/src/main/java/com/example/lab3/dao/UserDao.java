package com.example.lab3.dao;

import com.example.lab3.dto.UserDto;
import com.example.lab3.entity.UserEntity;
import com.example.lab3.interfaces.BaseDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

@Named(value = "userDao")
@ApplicationScoped
@NoArgsConstructor
@ToString
public class UserDao implements Serializable {
    private static final long serialVersionUID = -6680734166634363295L;

    public void create(UserDto userDto, Session session) {
        session.save(new UserEntity(userDto));
    }

    public List<UserEntity> getByEmail(Session session, String data) {
        Query<UserEntity> query = session.createQuery("from UserEntity user where user.email = :value ", UserEntity.class);
        query.setParameter("value", data);
        return query.getResultList();
    }

}
