package com.FinalProject.Coders.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO implements DTO {
    private Long id;
    private String filename;
    private String contentType;
    private Date createdAt;
    private Date modifiedAt;
    private String photo;
}