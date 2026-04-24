package com.darknightcoder.service.impl;

import com.darknightcoder.client.ProductServiceClient;
import com.darknightcoder.dto.ProductResponseDto;
import com.darknightcoder.exception.RateLimitException;
import com.darknightcoder.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductFetchService {
    private final ProductServiceClient productServiceClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
    @Retry(name = "productService")
    @RateLimiter(name = "productService")
    public ProductResponseDto fetchProduct(UUID productId){
        return productServiceClient.getProductById(productId);
    }
    public ProductResponseDto productFallback(UUID productId, Exception ex){
        log.error("Fallback triggered...{} reason {}",ex.getClass(),ex.getMessage());
        if (ex instanceof RequestNotPermitted){
            throw new RateLimitException("Too many request - please try shortly");
        }
        if (ex instanceof CallNotPermittedException){
            throw new ServiceUnavailableException("Product service limit exceed,");
        }
        throw new ServiceUnavailableException("Product");
    }
}
