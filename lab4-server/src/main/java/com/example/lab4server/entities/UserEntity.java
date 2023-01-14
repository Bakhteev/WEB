package com.example.lab4server.entities;

import com.example.lab4server.dto.AuthDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("users")
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String passwordHash;
}
