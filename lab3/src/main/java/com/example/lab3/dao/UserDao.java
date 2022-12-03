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
public class UserDao implements BaseDao<UserEntity, UserDto>, Serializable {
    private static final long serialVersionUID = -6680734166634363295L;

    @Override
    public void create(UserDto userDto, Session session) {
        session.save(new UserEntity(userDto));
    }

    @Override
    public List<UserEntity> getAll(Session session) {
        return null;
    }

    @Override
    public <T> List<UserEntity> getBy(Session session, String field, T data) {
        Query<UserEntity> query = session.createQuery("from UserEntity user where user.email = :value ", UserEntity.class);
        query.setParameter("value", data);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

}
