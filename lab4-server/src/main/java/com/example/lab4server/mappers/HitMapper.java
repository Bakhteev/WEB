package com.example.lab4server.mappers;

import com.example.lab4server.dto.response.HitResponseDto;
import com.example.lab4server.entities.HitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HitMapper {
    HitMapper INSTANCE = Mappers.getMapper(HitMapper.class);

    HitResponseDto toDTO(HitEntity hitEntity);

    List<HitResponseDto> toDTO(List<HitEntity> hitEntity);

    HitEntity toHitEntity(HitResponseDto pointResponseDto);
}
