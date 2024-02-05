package com.FinalProject.Coders.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorSearchRequest {
    private String name;
    private String surname;
    private String speciality;
}
