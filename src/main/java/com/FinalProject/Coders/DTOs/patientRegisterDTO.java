package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Blood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class patientRegisterDTO implements DTO{
    private String userEmail;
    private Blood blood;
    private Integer height;
    private Integer weight;
    private List<String> currentDiseases;
    private Boolean isTakingMedication;
}
