package com.FinalProject.Coders.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetTreatmentsDTO {
    private String doctorPhoto;
    private String hospital;
    private String doctorSpeciality;
    private String diagnose;
    private String appointmentDate;
    private String appointmentTime;
}
