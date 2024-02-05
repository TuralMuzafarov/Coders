package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String cardNumber;
    private String name;
    private Country country;
}