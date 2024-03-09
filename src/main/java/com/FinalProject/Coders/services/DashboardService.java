package com.FinalProject.Coders.services;

import ch.qos.logback.core.net.ObjectWriter;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.DashboardUserDetailsDTO;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.repositories.AttachmentRepo;
import com.FinalProject.Coders.repositories.PatientRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class DashboardService {
    private final UserRepo userRepo;
    private final AttachmentRepo attachmentRepo;
    private final PatientRepo patientRepo;
    private final AttachmentService attachmentService;

    @Transactional
    public GeneralDTO gettingUserDetails(UserEntity user) {
        String photo;
        GeneralDTO DTO = new GeneralDTO();
        if(user.getAttachment()!= null)
        {
            String photoPath = user.getAttachment().getPhoto();
            photo = attachmentService.getPhoto(photoPath);
        }else{
            photo = "";
        }
        DashboardUserDetailsDTO userDetails = DashboardUserDetailsDTO.builder()
                .name(user.getFirstName())
                .surname(user.getLastName())
                .age(user.getAge())
                .height(user.getPatientInfo().getHeight())
                .weight(user.getPatientInfo().getWeight())
                .photo(photo)
                .blood(user.getPatientInfo().getBlood().toString())
                .build();
        DTO.setDTO(userDetails);
        DTO.setStatusCode(HttpStatus.ACCEPTED);
        return DTO;
    }
}
