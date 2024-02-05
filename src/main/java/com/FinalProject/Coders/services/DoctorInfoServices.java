package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.DoctorSearchRequest;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.entities.DoctorInfo;
import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorInfoServices {
    private final DoctorInfoRepo doctorInfoRepo;
    public GeneralDTO getDoctorAvailableDaysOfTheWeek(DoctorSearchRequest req)
    {
        GeneralDTO DTO = new GeneralDTO();
        try
        {
            String name = req.getName();
            String surname = req.getSurname();
            String speciality = req.getSpeciality();
            DoctorInfo doctor = doctorInfoRepo.findDoctorInfoByNameAndSurnameAndSpeciality(name, surname, speciality).orElseThrow(() ->new Exception("Doctor Not Found"));
            Set<DayOfWeek> availableDayOfTheWeek = doctor.getWorkingDays();
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            DTO.setDTO(availableDayOfTheWeek);
            return DTO;
        }catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            return DTO;
        }


    }
}
