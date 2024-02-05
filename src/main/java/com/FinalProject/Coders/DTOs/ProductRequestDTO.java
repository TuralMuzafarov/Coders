package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.ProductCategories;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String productName;
    private Float price;
    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductCategories category;
}
