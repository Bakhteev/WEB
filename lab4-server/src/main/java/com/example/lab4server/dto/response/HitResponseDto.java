package com.example.lab4server.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HitResponseDto {
    private String id;
    private float xValue;
    private float yValue;
    private int rValue;
    private boolean hit;
    private String date;
    private long leadTime;
}
