package com.darknightcoder.controller;

import com.darknightcoder.dto.CreateProductDto;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.dto.ProductsResponseDto;
import com.darknightcoder.dto.UpdateProductRequestDto;
import com.darknightcoder.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductDto createProductDto){
        return new ResponseEntity<>(productService.createProduct(createProductDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<ProductResponseDto> getProductById(@PathVariable UUID id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductsResponseDto> listOfAllProducts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
       ProductsResponseDto responseDto = productService.getAllProducts(pageNo,pageSize,sortBy,sortDir);
       return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable UUID id,
                                                                 @RequestBody UpdateProductRequestDto updateProductDto){
        return new ResponseEntity<>(productService.updateProduct(id, updateProductDto),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
