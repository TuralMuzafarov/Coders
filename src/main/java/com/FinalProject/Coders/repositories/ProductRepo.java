package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.Enums.ProductCategories;
import com.FinalProject.Coders.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product , Long> {
    Page<Product> findProductByCategory(ProductCategories category , Pageable page);
}
