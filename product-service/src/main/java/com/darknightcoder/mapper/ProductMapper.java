package com.darknightcoder.mapper;

import com.darknightcoder.dto.CreateProductDto;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.dto.UpdateProductRequestDto;
import com.darknightcoder.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product mapToEntity(CreateProductDto createProductDto);

    ProductResponseDto mapToDto(Product product);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateProductRequestDto request, @MappingTarget Product product);

}
