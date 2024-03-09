package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.DTO;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.PaginationRequestDTO;
import com.FinalProject.Coders.DTOs.addMealDTO;
import com.FinalProject.Coders.Enums.MealCategory;
import com.FinalProject.Coders.entities.Meal;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.services.AttachmentService;
import com.FinalProject.Coders.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final AttachmentService attachmentService;

    @PostMapping(value = "/addImage")
    public GeneralDTO addMealImage(@RequestParam("file") MultipartFile image)
    {
        return attachmentService.uploadMealImage(image);
    }

    @PostMapping(value = "/addMeal")
    public ResponseEntity<GeneralDTO> addMealText(@RequestBody addMealDTO req)
    {
        GeneralDTO DTO = mealService.addMeal(req);
        if(DTO.getStatusCode() != HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(DTO);
        }
        return ResponseEntity.ok().body(DTO);
    }

    @GetMapping(value = "/getMeals/{category}")
    public ResponseEntity<GeneralDTO> getMealByCategory(@PathVariable("category") MealCategory category
            , @RequestBody PaginationRequestDTO DTO)
    {
        Pageable page = DTO.getPageable(DTO);
        GeneralDTO generalDTO = mealService.getMealsByCategory(category ,page);
        if(generalDTO.getStatusCode() == HttpStatus.ACCEPTED)
        {
            return ResponseEntity.ok().body(generalDTO);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generalDTO);
    }

    @PostMapping("/eat/{mealId}")
    public ResponseEntity<GeneralDTO> eatMeal(@PathVariable Long mealId)
    {
        UserEntity user =(UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null)
        {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
        GeneralDTO DTO = mealService.eatMeal(mealId , user);
        if(DTO.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO);
        }
        return ResponseEntity.ok().body(DTO);
    }

    @GetMapping("/getEaten/{category}")
    public ResponseEntity<GeneralDTO> getEatenByCategory(@PathVariable MealCategory category)
    {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GeneralDTO dto = mealService.getEatenMealsByCategory(category , user);
        if(user == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
        }
        if(dto.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
        }
        return ResponseEntity.ok().body(dto);

    }
}

