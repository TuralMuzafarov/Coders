package com.FinalProject.Coders.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class RegisterDoctorDTO {
    private String name;
    private String surname;
    private String email;
    private String hospital;
    private String speciality;
    private Set<DayOfWeek> availableDays;

}
