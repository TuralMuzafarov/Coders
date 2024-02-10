package com.FinalProject.Coders.DTOs;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTreatmentsDTO {
    private String doctorPhoto;
    private String hospital;
    private String doctorSpeciality;
    private String diagnose;
    private String appointmentDate;
    private String appointmentTime;
}
