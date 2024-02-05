package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.RegisterResponse;
import com.FinalProject.Coders.entities.Attachment;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.services.AttachmentService;
import com.FinalProject.Coders.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final AttachmentService attachmentService;

    public UserController(UserService userService, AttachmentService attachmentService) {
        this.userService = userService;
        this.attachmentService = attachmentService;
    }

    @GetMapping("/me")
    public ResponseEntity<GeneralDTO> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity registeredUser = (UserEntity) authentication.getPrincipal();
        GeneralDTO DTO = new GeneralDTO();
        DTO.setStatusCode(HttpStatus.ACCEPTED);
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

        DTO.setDTO(registerResponse);


        return ResponseEntity.ok(DTO);
    }

    @PostMapping(value = "/upload")
    private GeneralDTO uploadProfile(@RequestParam("file") MultipartFile req){
        UserEntity user =(UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GeneralDTO DTO =  attachmentService.uploadImage(req , user);
        return DTO;
    }

    @GetMapping("/")
    public ResponseEntity<GeneralDTO> allUsers() {
        List <UserEntity> users = userService.allUsers();
        GeneralDTO DTO = new GeneralDTO();
        DTO.setDTO(users);
        DTO.setStatusCode(HttpStatus.ACCEPTED);
        return ResponseEntity.ok(DTO);
    }


}
