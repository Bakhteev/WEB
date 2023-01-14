package com.example.lab4server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshAuthenticatedResponseDto {
    private String accessToken;
    private String refreshToken;
    private final String tokenType = "Bearer";
}
