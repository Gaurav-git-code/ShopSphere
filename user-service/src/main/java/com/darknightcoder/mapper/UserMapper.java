package com.darknightcoder.mapper;

import com.darknightcoder.dto.RegisterRequestDto;
import com.darknightcoder.dto.UserResponseDto;
import com.darknightcoder.entity.ShopSphereUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(ShopSphereUser user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password",ignore = true)
    ShopSphereUser toEntity(RegisterRequestDto requestDto);
}
