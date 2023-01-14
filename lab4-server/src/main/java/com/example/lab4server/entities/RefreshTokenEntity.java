package com.example.lab4server.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document("refreshTokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    private Instant expiryDate;
    @NotBlank
    @Indexed(unique = true)
    private String token;
}
