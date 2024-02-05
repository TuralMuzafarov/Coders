package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Position;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private long id;

    @Enumerated(EnumType.STRING)
    private Position position;

    private String hospital;

}
