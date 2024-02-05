package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.Enums.ProductCategories;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Float price;
    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductCategories category;
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    @UpdateTimestamp
//    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();
}
