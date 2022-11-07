package com.example.lab2.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -1637976018507092504L;
    private String email;
    private String password;

    public UserDto() {
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
