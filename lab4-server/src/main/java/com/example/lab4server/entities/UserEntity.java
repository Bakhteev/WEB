package com.example.lab4server.entities;

import com.example.lab4server.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("users")
public class UserEntity {
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
