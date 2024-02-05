package com.FinalProject.Coders.DTOs;


import com.FinalProject.Coders.Enums.Blood;
import com.FinalProject.Coders.Enums.Gender;
import com.FinalProject.Coders.Enums.Role;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private Role role;
    private Gender gender;
    private Blood blood;
    private Integer height;
    private Integer weight;
    private Boolean isTakingMedication;
    private Integer caloriesBurned;
    private Integer currentStepCount;
    private Integer desiredKg;
}
