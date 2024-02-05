package com.FinalProject.Coders.DTOs;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralDTO implements DTO{
    private HttpStatus StatusCode;
    private Object DTO;
}
