package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.ExaminationStatus;
import com.FinalProject.Coders.Enums.TreatmentStatus;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class LastExaminationDTO {
    private String appointmentDate;
    private ExaminationStatus status;
    private boolean needAnalyze;
    private String diagnose;
}
