package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.PatientInfo;
import com.FinalProject.Coders.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends CrudRepository<UserEntity , Long> {
    Optional<UserEntity> findByEmail(String email);
    @Query("UPDATE UserEntity u SET u.patientInfo = :patientInfo WHERE u.id = :userId")
    void updatePatientInfo(@Param("userId") Long userId, @Param("patientInfo") PatientInfo patientInfo);

    


}

