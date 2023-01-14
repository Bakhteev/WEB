package com.example.lab4server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionMessageDto {
    private int statusCode;
    private String message;
}
