package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepo extends JpaRepository<Treatment, Long> {
}
