package com.example.lab3.state;

import com.example.lab3.dto.UserDto;
import com.example.lab3.entity.UserEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Named(value = "userState")
@SessionScoped
@Data
@NoArgsConstructor
public class UserState implements Serializable {
    private static final long serialVersionUID = -6677734133634363295L;

    private UserEntity user;
}
