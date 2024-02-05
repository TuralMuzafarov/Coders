package com.FinalProject.Coders.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.SupportedSourceVersion;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class DoctorAvailabilityDTO {
    private String name;
    private String surname;
    private String speciality;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String Date;
}
