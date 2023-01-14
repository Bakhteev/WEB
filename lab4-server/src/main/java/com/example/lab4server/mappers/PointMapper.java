package com.example.lab4server.mappers;

import com.example.lab4server.dto.PointDto;
import com.example.lab4server.entities.PointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    PointDto toDTO(PointEntity pointEntity);

    PointEntity toPointEntity(PointDto pointDto);
}
