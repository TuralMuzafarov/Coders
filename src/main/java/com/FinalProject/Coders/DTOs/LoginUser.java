package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Blood;
import lombok.Data;

@Data
public class LoginUser implements DTO{
    private String email;
    private String password;
}
