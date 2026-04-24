package com.darknightcoder.dto;

import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsResponseDto {
    private List<ProductResponseDto> products;
    private int pageNo;
    private int pageSize;
    private Long totalElement;
    private int totalPage;
    private boolean isLast;
}
