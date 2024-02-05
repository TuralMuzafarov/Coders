package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.AppointmentDTO;
import com.FinalProject.Coders.DTOs.AppointmentRegisterDTO;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
