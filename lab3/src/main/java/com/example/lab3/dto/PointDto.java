package com.example.lab3.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PointDto {
    private double xValue;
    private double yValue;
    private int rValue;
    private String hit;
    private String date;
    private long leadTime;
}
