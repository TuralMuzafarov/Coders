package com.FinalProject.Coders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUserDetailsDTO implements DTO{
    private String name;
    private String surname;
    private String blood;
    private Integer height;
    private Integer weight;
    private String photo;
    private Integer age;
}
