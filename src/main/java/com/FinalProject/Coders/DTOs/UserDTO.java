package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.Gender;
import com.FinalProject.Coders.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements DTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private Role userRole;
    private AttachmentDTO attachment;
    private PatientDTO patientInfo;
}