package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.PaginationRequestDTO;
import com.FinalProject.Coders.DTOs.ProductRequestDTO;
import com.FinalProject.Coders.DTOs.ProductResponse;
import com.FinalProject.Coders.Enums.ProductCategories;
import com.FinalProject.Coders.entities.Product;
import com.FinalProject.Coders.repositories.ProductRepo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepo productRepo;

    public GeneralDTO addProduct(ProductRequestDTO product)
    {
        GeneralDTO generalDTO = new GeneralDTO();
        try {
            Product product1 = Product.builder()
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .unit(product.getUnit())
                    .category(product.getCategory())
                    .build();

            productRepo.save(product1);
            generalDTO.setStatusCode(HttpStatus.ACCEPTED);
            return generalDTO;
        }catch (Exception e)
        {
            e.printStackTrace();
            generalDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return generalDTO;
        }
    }

    public GeneralDTO getThreeProductByCategory(ProductCategories category)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            Pageable page = PageRequest.of(0, 3);
            Page<Product> products = productRepo.findProductByCategory(category, page);
            List<ProductResponse> productResponseList = products.stream().map(ProductResponse::mapperFromProduct).toList();
            DTO.setDTO(new PageImpl<>(productResponseList, page, products.getTotalElements()));
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        }catch (IllegalArgumentException | InvalidDataAccessApiUsageException e) {
            // Handle the exception
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        } catch (Exception e) {
            // Handle other exceptions
            DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return DTO;
        }
    }

    public GeneralDTO getAllProductByCategory(ProductCategories category , PaginationRequestDTO pageReq)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            Pageable page = pageReq.getPageable(pageReq);
            Page<Product> products = productRepo.findProductByCategory(category, page);
            List<ProductResponse> productResponseList = products.stream().map(ProductResponse::mapperFromProduct).toList();
            DTO.setDTO(new PageImpl<>(productResponseList, page, products.getTotalElements()));
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        }catch (IllegalArgumentException | InvalidDataAccessApiUsageException e) {
            // Handle the exception
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        } catch (Exception e) {
            // Handle other exceptions
            DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return DTO;
        }
    }


}
