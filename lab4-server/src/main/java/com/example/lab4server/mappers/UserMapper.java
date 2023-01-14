package com.example.lab4server.mappers;


import com.example.lab4server.dto.UserDto;
import com.example.lab4server.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDto authDto);
}
