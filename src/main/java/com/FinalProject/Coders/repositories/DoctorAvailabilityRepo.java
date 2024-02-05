package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability , Long> {
    Optional<DoctorAvailability>findDoctorAvailabilitiesByDate(LocalDate date);
}
