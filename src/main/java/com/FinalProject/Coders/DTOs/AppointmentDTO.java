package com.FinalProject.Coders.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;

    private String startTime;
    private Long doctorId;
}