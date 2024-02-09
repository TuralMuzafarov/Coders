package com.FinalProject.Coders.services;


import com.FinalProject.Coders.DTOs.GeneralDTO;

import com.FinalProject.Coders.entities.Attachment;
import com.FinalProject.Coders.entities.Meal;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.repositories.AttachmentRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final UserRepo userRepo;
    private final AttachmentRepo attachmentRepo;

    @Transactional()
    public GeneralDTO uploadImage(MultipartFile file , UserEntity user){
        GeneralDTO DTO = new GeneralDTO();
        Attachment attachment;
        if(user.getAttachment() == null)
        {
            attachment = new Attachment();
        }
        else attachment = user.getAttachment();


        if (file.isEmpty()) {
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }

        try {
            String uploadDir = "C:\\Users\\Tural\\Desktop\\Coders\\src\\main\\java\\com\\FinalProject\\Coders\\attachments\\profilePhotos";
            // Create the directory if it doesn't exist
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            assert fileName != null;
            if (fileName.contains(".")) {
                fileName =  StringUtils.stripFilenameExtension(fileName);
            }
            String contentType = file.getContentType();
            String filePath = uploadDir + File.separator + fileName;



            attachment.setPhoto(filePath);
            attachment.setContentType(contentType);
            attachment.setFilename(fileName);

            File dest = new File(filePath);
            file.transferTo(dest);

            attachmentRepo.save(attachment);
            user.setAttachment(attachment);
            userRepo.save(user);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        } catch (IOException e) {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return DTO;
        }
    }

    public GeneralDTO uploadMealImage(MultipartFile file)
    {
        GeneralDTO DTO = new GeneralDTO();
        Attachment attachment = new Attachment();
        if (file.isEmpty()) {
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }

        try {
            String uploadDir = "C:\\Users\\Tural\\Desktop\\CodersProject\\Coders\\src\\main\\java\\com\\FinalProject\\Coders\\attachments\\meals";
            // Create the directory if it doesn't exist


            String originalFileName = file.getOriginalFilename();
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            if (fileName.contains(".")) {
                fileName =  StringUtils.stripFilenameExtension(fileName);
            }
            String contentType = file.getContentType();
            String filePath = uploadDir + File.separator + originalFileName;


            attachment.setPhoto(filePath);
            attachment.setContentType(contentType);
            attachment.setFilename(fileName);

            File dest = new File(filePath);
            file.transferTo(dest);

            attachmentRepo.save(attachment);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        } catch (IOException e) {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return DTO;
        }
    }


    public String getPhoto(String photoPath)
    {
        Path imagePath = Paths.get(photoPath);
        try {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64image = Base64.getEncoder().encodeToString(imageBytes);
            return base64image;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
