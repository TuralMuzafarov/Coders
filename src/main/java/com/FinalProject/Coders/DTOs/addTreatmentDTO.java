package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.ExaminationStatus;
import com.FinalProject.Coders.Enums.TreatmentStatus;
import com.FinalProject.Coders.entities.Medicine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class addTreatmentDTO {
    private String diagnose;
    private Boolean needAnalyze;
    private ExaminationStatus status;

    private List<MedicineDTO> medicineList;
}
