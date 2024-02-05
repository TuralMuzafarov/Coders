package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.ProductCategories;
import com.FinalProject.Coders.entities.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Float price;
    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductCategories category;

    public static ProductResponse mapperFromProduct(Product product)
    {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .unit(product.getUnit())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}


