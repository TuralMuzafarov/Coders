package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Blood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements DTO {
    private Long id;
    private Blood blood;
    private Integer height;
    private Integer weight;
    private List<String> currentDiseases;
    private Boolean isTakingMedication;
    private Date createdAt;
    private Date updatedAt;
    private Integer currentStepCount;
    private Integer caloryBurned;
    private Integer desiredKg;


}
