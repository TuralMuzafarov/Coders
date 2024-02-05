package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.patientRegisterDTO;
import com.FinalProject.Coders.entities.PatientInfo;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.mapper.ModelMapperUtil;
//import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import com.FinalProject.Coders.repositories.PatientRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional
    public boolean savePatient(patientRegisterDTO patient)
    {
        System.out.println("service working...");
        UserEntity user = userRepo.findByEmail(patient.getUserEmail()).orElse(null);

        if(user == null)
        {
            System.out.println("user null");
            return false;
        }
        else
        {
            System.out.println("In patient service");
            System.out.println("user found");
            PatientInfo patientInfo = new PatientInfo();
            System.out.println("In patient service");
            patientInfo.setBlood(patient.getBlood());
            System.out.println("In patient service");
            patientInfo.setHeight(patient.getHeight());
            System.out.println("In patient service");
            patientInfo.setWeight(patient.getWeight());
            System.out.println("In patient service");
            patientInfo.setIsTakingMedication(patient.getIsTakingMedication());
            System.out.println("In patient service");
            patientInfo.setCurrentDiseases(patient.getCurrentDiseases());
            System.out.println("In patient service");
            patientInfo.setUserEntity(user);
            System.out.println("In patient service");
            patientRepo.save(patientInfo);
            System.out.println("In patient service");

            return true;
        }


    }
}
