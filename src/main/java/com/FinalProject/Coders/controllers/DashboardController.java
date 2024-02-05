package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FinalProject.Coders.services.DashboardService;

@RestController
@RequestMapping(value = "/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping(value = "/userDetails")
    public ResponseEntity<GeneralDTO> getUserDetails()
    {
        UserEntity user =(UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            GeneralDTO DTO = dashboardService.gettingUserDetails(user);
            return ResponseEntity.ok().body(DTO);
        }catch (Exception e)
        {
            GeneralDTO DTO = new GeneralDTO();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
    }

}
