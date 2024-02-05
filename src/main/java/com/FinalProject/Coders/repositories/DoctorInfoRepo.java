package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Optional;

@Repository
public interface DoctorInfoRepo extends JpaRepository<DoctorInfo , Long> {
    Optional<DoctorInfo> findDoctorInfoByNameAndSurnameAndSpeciality(String name , String Surname , String speciality);
}
