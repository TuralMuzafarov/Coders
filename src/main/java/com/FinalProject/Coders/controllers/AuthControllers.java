package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.*;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.repositories.UserRepo;
import com.FinalProject.Coders.security.JwtService;
import com.FinalProject.Coders.services.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthControllers {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<GeneralDTO> register(@RequestBody RegisterUser registerUserDto) {
        UserEntity registeredUser = authenticationService.signUp(registerUserDto);
        RegisterResponse registerResponse = RegisterResponse.builder()
                .id(registeredUser.getId())
                .firstName(registeredUser.getFirstName())
                .lastName(registeredUser.getLastName())
                .email(registeredUser.getEmail())
                .password(registeredUser.getPassword())
                .age(registeredUser.getAge())
                .gender(registeredUser.getGender())
                .role(registeredUser.getUserRole())
                .blood(registeredUser.getPatientInfo().getBlood())
                .height(registeredUser.getPatientInfo().getHeight())
                .weight(registeredUser.getPatientInfo().getWeight())
                .currentStepCount(registeredUser.getPatientInfo().getCurrentStepCount())
                .desiredKg(registeredUser.getPatientInfo().getDesiredKg())
                .caloriesBurned(registeredUser.getPatientInfo().getCaloryburned())
                .isTakingMedication(registeredUser.getPatientInfo().getIsTakingMedication())
                .build();

        GeneralDTO DTO = new GeneralDTO();
        DTO.setStatusCode(HttpStatus.ACCEPTED);
        DTO.setDTO(registerResponse);
        return ResponseEntity.ok(DTO);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralDTO> authenticate(@RequestBody LoginUser loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        GeneralDTO DTO = new GeneralDTO();
        DTO.setStatusCode(HttpStatus.ACCEPTED);
        DTO.setDTO(loginResponse);
        return ResponseEntity.ok(DTO);
    }


    @PostMapping("/signupAdmin")
    public ResponseEntity<GeneralDTO> registerAdmin(@RequestBody RegisterUser registerUserDto) {
        UserEntity registeredUser = authenticationService.signUpAdmin(registerUserDto);
        GeneralDTO DTO = new GeneralDTO();
        DTO.setDTO(registeredUser);
        DTO.setStatusCode(HttpStatus.ACCEPTED);
        return ResponseEntity.ok(DTO);
    }

    @Transactional
    @PostMapping("/authorizeDoctor")
    public ResponseEntity<GeneralDTO> authorizeDoctor(@RequestBody RegisterDoctorDTO registerDTO)
    {
        GeneralDTO DTO1 = new GeneralDTO();
        try {
            DTO1 = authenticationService.registerDoctor(registerDTO);
            return ResponseEntity.ok().body(DTO1);
        }catch (Exception e)
        {
            DTO1.setStatusCode(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO1);
        }

    }


}
