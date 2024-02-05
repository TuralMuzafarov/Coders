package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Blood;
import com.FinalProject.Coders.Enums.Gender;
import lombok.Data;

@Data
public class RegisterUser implements DTO{
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer age;
    private Gender gender;
    private Blood blood;
    private Integer height;
    private Integer weight;
}
