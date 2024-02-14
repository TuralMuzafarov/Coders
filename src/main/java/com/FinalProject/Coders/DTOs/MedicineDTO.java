package com.FinalProject.Coders.DTOs;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MedicineDTO {
    private String medicineName;
    private String dose;
    private List<String> takenDays;
    private String postScript;

}