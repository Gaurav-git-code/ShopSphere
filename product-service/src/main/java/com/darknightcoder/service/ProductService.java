package com.darknightcoder.service;

import com.darknightcoder.dto.CreateProductDto;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.dto.ProductsResponseDto;
import com.darknightcoder.dto.UpdateProductRequestDto;

import java.util.UUID;

public interface ProductService {
    ProductResponseDto createProduct(CreateProductDto createProductDto);
    ProductResponseDto getProductById(UUID id);
    ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductResponseDto updateProduct(UUID id, UpdateProductRequestDto updateProductDto);
    void deleteProduct(UUID id);

}
