package com.example.lab4.entities;

import com.example.lab4.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity implements Serializable {
    @Id
    private String id;

    private String email;

    private String passwordHash;

//    private List<PointEntity> hits;

    public UserEntity(UserDto userDto) {
        this.email = userDto.getEmail();
        this.passwordHash = userDto.getPassword();
//        this.hits = new LinkedList<>();
    }
}
