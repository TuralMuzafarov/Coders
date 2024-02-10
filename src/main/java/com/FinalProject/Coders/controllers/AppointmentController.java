package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.*;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping(value = "/register")
    public ResponseEntity<GeneralDTO> registerAppointment(@RequestBody AppointmentRegisterDTO req)
    {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GeneralDTO DTO = appointmentService.registerAppointment(req , user);
        return ResponseEntity.ok().body(DTO);
    }


    @PostMapping(value = "/addTreatment/{id}")
    public ResponseEntity<GeneralDTO> addTreatment(@PathVariable Long id , @RequestBody addTreatmentDTO req)
    {
        GeneralDTO DTO = appointmentService.addTreatment(id , req);
        if(DTO.getStatusCode() == HttpStatus.ACCEPTED)
        {
            return ResponseEntity.ok().body(DTO);
        }
        if(DTO.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DTO);
        }
        if(DTO.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(DTO);
    }

    @PostMapping(value = "/getLastExaminations")
    public ResponseEntity<GeneralDTO> getLastExaminations(@RequestBody PaginationRequestDTO pageReq)
    {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GeneralDTO());
        }

        GeneralDTO DTO = appointmentService.getLastExaminations(user , pageReq);
        if(DTO.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
        return ResponseEntity.ok().body(DTO);
    }

    @GetMapping(value = "/getTreatments")
    public ResponseEntity<GeneralDTO> getTreatments(@RequestBody PaginationRequestDTO pageReq)
    {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GeneralDTO());
        }

        GeneralDTO DTO = appointmentService.getTreatments(pageReq , user);
        if(DTO.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
        return ResponseEntity.ok().body(DTO);
    }

}
