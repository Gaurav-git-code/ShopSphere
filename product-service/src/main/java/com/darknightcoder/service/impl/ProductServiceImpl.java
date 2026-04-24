package com.darknightcoder.service.impl;

import com.darknightcoder.dto.CreateProductDto;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.dto.ProductsResponseDto;
import com.darknightcoder.dto.UpdateProductRequestDto;
import com.darknightcoder.entity.Product;
import com.darknightcoder.exception.ResourceNotFoundException;
import com.darknightcoder.mapper.ProductMapper;
import com.darknightcoder.repository.ProductRepository;
import com.darknightcoder.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductMapper mapper;
    private ProductRepository productRepository;
    @Override
    public ProductResponseDto createProduct(CreateProductDto createProductDto) {
        return mapper.mapToDto(productRepository.save(mapper.mapToEntity(createProductDto))) ;
    }

    @Override
    public ProductResponseDto getProductById(UUID id) {
        Product product =productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product","Id",id));

        return mapper.mapToDto(product);
    }

    @Override
    public ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> listOfProducts = productPage.getContent();
        List<ProductResponseDto> createProductDtoList =
                listOfProducts.stream().map(p -> mapper.mapToDto(p)).toList();

        return ProductsResponseDto.builder()
                .products(createProductDtoList)
                .pageNo(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElement(productPage.getTotalElements())
                .totalPage(productPage.getTotalPages())
                .isLast(productPage.isLast())
                .build();
    }

    @Override
    public ProductResponseDto updateProduct(UUID id, UpdateProductRequestDto updateProductDto) {
        Product savedProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Id",id)
        );
        mapper.updateEntity(updateProductDto,savedProduct);
        return mapper.mapToDto(productRepository.save(savedProduct));
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product","id",id));
        productRepository.deleteById(id);
    }

}
