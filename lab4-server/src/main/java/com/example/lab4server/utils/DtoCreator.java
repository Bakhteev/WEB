package com.example.lab4server.utils;

import com.example.lab4server.dto.request.CreateHitDto;
import com.example.lab4server.dto.response.HitResponseDto;
import com.example.lab4server.dto.response.HitsPageResponseDto;
import com.example.lab4server.entities.HitEntity;
import com.example.lab4server.mappers.HitMapper;
import org.springframework.data.domain.Page;

public class DtoCreator {
    public static HitResponseDto createDto(CreateHitDto createHitDto) {
        HitResponseDto dto = new HitResponseDto();
        dto.setXValue(createHitDto.getX());
        dto.setYValue(createHitDto.getY());
        dto.setRValue(createHitDto.getR());
//        return HitResponseDto.builder().xValue(createHitDto.getX())
//                .yValue(createHitDto.getY())
//                .rValue(createHitDto.getR())
//                .build();
        return dto;
    }

    public static HitsPageResponseDto createDto(Page<HitEntity> page) {
        return HitsPageResponseDto.builder()
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .data(HitMapper.INSTANCE.toDTO(page.getContent()))
                .build();
    }
}
