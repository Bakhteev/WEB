package com.example.lab4server.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PointDto {
    private double xValue;
    private double yValue;
    private int rValue;
    private String hit;
    private String date;
    private long leadTime;
}
