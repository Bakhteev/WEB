package com.example.lab4server.utils;

import com.example.lab4server.dto.CreatePointDto;
import com.example.lab4server.dto.PointDto;

public class DtoCreator {
    public static PointDto createDto(CreatePointDto createPointDto){
        return PointDto.builder().xValue(createPointDto.getX())
                .yValue(createPointDto.getY())
                .rValue(createPointDto.getR())
                .build();
    }
}
