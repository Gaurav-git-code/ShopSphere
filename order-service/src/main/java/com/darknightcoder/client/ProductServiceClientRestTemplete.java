package com.darknightcoder.client;

//@Component
//@AllArgsConstructor
public class ProductServiceClientRestTemplete {
//    private RestTemplate restTemplate;
//
//    private static final String PRODUCT_SERVICE_URL = "http://localhost:8082/api/v1/products/{id}";
//
//    public ProductResponseDto getProductById(UUID productId){
//        try{
//            ResponseEntity<ProductResponseDto> productResponse =
//                    restTemplate.getForEntity(PRODUCT_SERVICE_URL, ProductResponseDto.class, productId);
//            ProductResponseDto product = productResponse.getBody();
//            if (product == null) throw new ResourceNotFoundException("Product","id",productId);
//            return product;
//        }catch (HttpClientErrorException.NotFound exception){
//            throw new ResourceNotFoundException("Product","id",productId);
//        }catch (ResourceAccessException exception){
//            throw new ServiceUnavailableException("Product");
//        }
//
//    }
}
