package com.example.lab3.services;

import com.example.lab3.dao.UserDao;
import com.example.lab3.dto.UserDto;
import com.example.lab3.entity.PointEntity;
import com.example.lab3.entity.UserEntity;
import com.example.lab3.utils.TransactionManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.util.List;
@Named(value = "authService")
@ApplicationScoped
@NoArgsConstructor
public class AuthService {

    @Inject
    private UserDao userDao;



    public UserEntity getUserByEmail(String email) {
        List<UserEntity> data = TransactionManager.doInTransaction(session -> userDao.getByEmail(session,  email));
        if(data.size() == 0){
            return null;
        }
        return data.get(0);
    }

    public void addUser(UserDto userDto) {
        TransactionManager.doInTransaction(session -> {
            userDao.create(userDto, session);
            return userDto;
        });
    }
}
