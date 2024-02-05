package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.PatientInfo;
import com.FinalProject.Coders.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<PatientInfo , Long> {
    Optional<PatientInfo> findByUserEntity(UserEntity user);
}
