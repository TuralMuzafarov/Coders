package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.*;
import com.FinalProject.Coders.Enums.Role;
//import com.FinalProject.Coders.entities.DoctorInfo;
import com.FinalProject.Coders.entities.DoctorInfo;
import com.FinalProject.Coders.entities.PatientInfo;
import com.FinalProject.Coders.entities.UserEntity;
//import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import com.FinalProject.Coders.repositories.PatientRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final DoctorInfoRepo doctorInfoRepo;
//    private final DoctorInfoRepo doctorInfoRepo;

    @Transactional
    public UserEntity signUp(RegisterUser input)
    {
        UserEntity user = new UserEntity();
        PatientInfo patient = new PatientInfo();
        patient.setBlood(input.getBlood());
        patient.setWeight(input.getWeight());
        patient.setHeight(input.getHeight());
        user.setFirstName(input.getFirstname());
        user.setLastName(input.getLastname());
        user.setEmail(input.getEmail());
        user.setAge(input.getAge());
        user.setUserRole(Role.USER);
        user.setGender(input.getGender());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        patient.setUserEntity(user);
        patient.setIsTakingMedication(false);
        patient.setCaloryburned(0);
        patient.setCurrentStepCount(0);
        patient.setDesiredKg(0);
        user.setPatientInfo(patient);
        userRepo.save(user);
        patientRepo.save(patient);
        return user;
    }

    public UserEntity authenticate(LoginUser input)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(),
                        input.getPassword())
        );
        return userRepo.findByEmail(input.getEmail()).orElseThrow();
    }


    public UserEntity signUpAdmin(RegisterUser input)
    {
        UserEntity user = new UserEntity();
        user.setFirstName(input.getFirstname());
        user.setLastName(input.getLastname());
        user.setEmail(input.getEmail());
        user.setAge(input.getAge());
        user.setUserRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepo.save(user);
    }

    public GeneralDTO registerDoctor(RegisterDoctorDTO DTO)
    {
        GeneralDTO GeneralDTO1 = new GeneralDTO();
        try {
            UserEntity user = userRepo.findByEmail(DTO.getEmail()).
                    orElseThrow(()->new UsernameNotFoundException("user not found"));

            DoctorInfo doctorInfo = DoctorInfo.builder()
                    .name(DTO.getName())
                    .surname(DTO.getSurname())
                    .hospital(DTO.getHospital())
                    .speciality(DTO.getSpeciality())
                    .workingDays(DTO.getAvailableDays())
                    .build();

            user.setUserRole(Role.DOCTOR);
            user.setDoctorInfo(doctorInfo);
            userRepo.save(user);
            doctorInfoRepo.save(doctorInfo);
            GeneralDTO1.setStatusCode(HttpStatus.ACCEPTED);
            return GeneralDTO1;

        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
           GeneralDTO1.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
           return GeneralDTO1;
        }catch (NullPointerException e)
        {
           e.printStackTrace();
           GeneralDTO1.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
           return GeneralDTO1;
        }
    }

}
