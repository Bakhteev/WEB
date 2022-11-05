package com.example.lab2.state;

import com.example.lab2.models.User;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class UserState {
    LinkedList<User> users = new LinkedList<>();
    private int currentId = 0;

    public void addUser(User user) {
        user.setId(++currentId);
        users.add(user);
        users.sort(Comparator.comparing(User::getId));
    }

    public User getUserByEmail(String email) {
        User userFound = null;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                userFound = user;
            }
        }
//        if (userFound == null) {
//            throw new NoSuchElementException("User not Found");
//        }
        return userFound;
    }

    public User getUserById(Integer id) {
        User userFound = null;
        for (User user : users) {
            if (user.getId().equals(id)) {
                userFound = user;
            }
        }
        if (userFound == null) {
            throw new NoSuchElementException("User not Found");
        }
        return userFound;
    }
}
