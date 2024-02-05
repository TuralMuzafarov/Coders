package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.DTO;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.patientRegisterDTO;
import com.FinalProject.Coders.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        System.out.println("Controller working!");
        this.patientService = patientService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<GeneralDTO> savePatient(@RequestBody patientRegisterDTO patient)
    {
        System.out.println("Controller Working...");
        GeneralDTO DTO = new GeneralDTO();
        boolean serviceResponse = patientService.savePatient(patient);
        if(serviceResponse)
        {
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return ResponseEntity.ok(DTO);
        }
        else {
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
    }
}
