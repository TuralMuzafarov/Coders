package com.FinalProject.Coders.repositories;

import com.FinalProject.Coders.Enums.MealCategory;
import com.FinalProject.Coders.entities.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepo extends JpaRepository<Meal, Long> {
    Page<Meal> findAllByCategory(MealCategory category , Pageable pageable);
    Optional<Meal> findMealByMealName(String mealName);
}
