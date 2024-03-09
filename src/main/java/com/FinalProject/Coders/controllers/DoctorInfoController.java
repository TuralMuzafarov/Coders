package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.DoctorAvailabilityDTO;
import com.FinalProject.Coders.DTOs.DoctorSearchRequest;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.IdRequest;
import com.FinalProject.Coders.mapper.TimestampMapper;
import com.FinalProject.Coders.services.DoctorAvailabilityService;
import com.FinalProject.Coders.services.DoctorInfoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.sql.Time;
import java.time.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/Doctor")
@RequiredArgsConstructor
public class DoctorInfoController {
    private final DoctorInfoServices doctorInfoServices;
    private final DoctorAvailabilityService doctorAvailabilityService;


    @GetMapping(value = "/calendar")
    public ResponseEntity<GeneralDTO> getDoctorAvailableDayOfTheWeek(@RequestBody DoctorSearchRequest req)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            DTO = doctorInfoServices.getDoctorAvailableDaysOfTheWeek(req);
            return ResponseEntity.ok().body(DTO);
        }catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DTO);
        }
    }

    @GetMapping(value = "/availableHours")
    public ResponseEntity<GeneralDTO> getAvailableHours(@RequestBody DoctorAvailabilityDTO req)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            String name = req.getName();
            String surname = req.getSurname();
            String speciality = req.getSpeciality();

            LocalDate Date = LocalDate.parse(req.getDate());

            List<String> times = doctorAvailabilityService.getAvailableTimes(name , surname , speciality , Date);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            System.out.println("2");
            DTO.setDTO(times);
            return ResponseEntity.ok().body(DTO);
        }catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
    }
}
