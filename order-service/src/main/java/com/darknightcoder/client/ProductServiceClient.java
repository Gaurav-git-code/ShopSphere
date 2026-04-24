package com.darknightcoder.client;

import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping(value = "/api/v1/products/{productId}")
    ProductResponseDto getProductById(@PathVariable UUID productId);

}
