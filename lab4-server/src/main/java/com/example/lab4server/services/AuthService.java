package com.example.lab4server.services;

import com.example.lab4server.dto.request.AuthDto;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.httpExcetions.BadRequestException;
import com.example.lab4server.httpExcetions.NotFountException;
import com.example.lab4server.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registration(AuthDto authDto) {
        Optional<UserEntity> userEntityOptional = userRepository.getUserByEmail(authDto.getEmail());
        if (userEntityOptional.isPresent()) {
            throw new BadRequestException("User is already registered");
        }
        String passwordHash = passwordEncoder.encode(authDto.getPassword());
        return userRepository.save(new UserEntity(new ObjectId().toHexString(), authDto.getEmail(), passwordHash));
    }

    public UserEntity login(AuthDto authDto) {
        Optional<UserEntity> userEntityOptional = userRepository.getUserByEmail(authDto.getEmail());
        if (userEntityOptional.isEmpty()) {
            throw new NotFountException("User with email: " + authDto.getEmail() + " is not registered");
        }
        UserEntity user = userEntityOptional.get();
        if (!passwordEquals(user.getPasswordHash(), authDto.getPassword())) {
            throw new BadRequestException("Wrong password or email");
        }
        return user;
    }


    private boolean passwordEquals(String password, String passwordNew) {
        return passwordEncoder.matches(passwordNew, password);
    }
}
