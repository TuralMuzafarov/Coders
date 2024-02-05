package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.*;
import com.FinalProject.Coders.Enums.ProductCategories;
import com.FinalProject.Coders.entities.Product;
import com.FinalProject.Coders.repositories.ProductRepo;
import com.FinalProject.Coders.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;
    private final ProductServices productServices;


    @PostMapping(value = "/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(@RequestBody PaginationRequestDTO dto) {
        try {
            Pageable pageable = dto.getPageable(dto);
            Page<Product> products = productRepo.findAll(pageable);
            List<ProductResponse> productResponses = products.stream()
                    .map(ProductResponse::mapperFromProduct)
                    .toList();
            return ResponseEntity.ok(productResponses);
        } catch (Exception e) {
            // Handle exceptions gracefully
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping(value = "/add")
    public GeneralDTO addProduct(@RequestBody ProductRequestDTO product)
    {
        GeneralDTO dto = productServices.addProduct(product);
        return dto;
    }

    @PostMapping(value = "/{category}")
    public ResponseEntity<GeneralDTO> getThreeProductByCategory(@PathVariable String category)
    {
        try {
            ProductCategories productCategories = ProductCategories.valueOf(category.toUpperCase());
            GeneralDTO DTO = productServices.getThreeProductByCategory(productCategories);



            if(DTO.getStatusCode() == HttpStatus.ACCEPTED)
            {
                return ResponseEntity.ok().body(DTO);
            }
            else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DTO);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/{category}/all")
    public ResponseEntity<GeneralDTO> getThreeProductByCategory(
            @PathVariable String category ,
            @RequestBody PaginationRequestDTO productShowByCategoryDTO)
    {
        try {
            ProductCategories productCategories = ProductCategories.valueOf(category.toUpperCase());
            GeneralDTO DTO = productServices.getAllProductByCategory(productCategories , productShowByCategoryDTO);



            if(DTO.getStatusCode() == HttpStatus.ACCEPTED)
            {
                return ResponseEntity.ok().body(DTO);
            }
            else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(DTO);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
