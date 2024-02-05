package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.Enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardNumber;
    private String name;
    @Enumerated(EnumType.STRING)
    private Country country;


    @ManyToOne
    private UserEntity user;
}
