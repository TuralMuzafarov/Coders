package com.FinalProject.Coders.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRegisterDTO {
    private String date;
    private String time;
    private Long doctorId;
}
