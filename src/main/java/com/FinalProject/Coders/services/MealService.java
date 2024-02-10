package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.DTOs.GetMealDTO;
import com.FinalProject.Coders.DTOs.addMealDTO;
import com.FinalProject.Coders.Enums.MealCategory;
import com.FinalProject.Coders.entities.Attachment;
import com.FinalProject.Coders.entities.Meal;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.repositories.AttachmentRepo;
import com.FinalProject.Coders.repositories.MealRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final AttachmentService attachmentService;
    private final MealRepo mealRepo;
    private final AttachmentRepo attachmentRepo;
    private final UserRepo userRepo;

    public GeneralDTO addMeal(addMealDTO req){
        GeneralDTO DTO = new GeneralDTO();
        try {
            Attachment mealAttachment = attachmentRepo.findAttachmentByFilename(req.getMealName()).orElse(null);
            Meal meal = Meal.builder()
                    .mealName(req.getMealName())
                    .fat(req.getFat())
                    .carbs(req.getCarbs())
                    .protein(req.getProtein())
                    .kcal(req.getKcal())
                    .category(req.getCategory())
                    .attachment(mealAttachment)
                    .build();

            mealRepo.save(meal);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            DTO.setDTO(meal);
            return DTO;
        }catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }

    }

    public GeneralDTO getMealsByCategory(MealCategory category , Pageable page)
    {
        GeneralDTO generalDTO = new GeneralDTO();
        try {
            Page<Meal> pageMeal =  mealRepo.findAllByCategory(category,page);
            List<Meal> listMeal = pageMeal.getContent();
            List<GetMealDTO> mealDTOS = listMeal.stream().map(meal -> {
                GetMealDTO DTO = new GetMealDTO();
                DTO.setMealName(meal.getMealName());
                DTO.setMealId(meal.getId());
                DTO.setBase64Photo(attachmentService.getPhoto(meal.getAttachment().getPhoto()));
                DTO.setKcal(meal.getKcal());
                return DTO;
            }).toList();

            generalDTO.setDTO(new PageImpl<>(mealDTOS, page, pageMeal.getTotalElements()));
            generalDTO.setStatusCode(HttpStatus.ACCEPTED);
            return generalDTO;
        }catch (Exception e)
        {
            e.printStackTrace();
            generalDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return generalDTO;
        }


    }

    public GeneralDTO eatMeal(Long mealId , UserEntity user)
    {
        GeneralDTO DTO = new GeneralDTO();
        try
        {
            Meal meal = mealRepo.findById(mealId).orElseThrow(NullPointerException::new);
            if(meal.getCategory() == MealCategory.BREAKFAST)
            {
                user.setBreakfastKcal(user.getBreakfastKcal() + meal.getKcal());
                user.setTotalCarbs(user.getTotalCarbs() + meal.getCarbs());
                user.setTotalProtein(user.getTotalProtein() + meal.getProtein());
                user.setTotalFat(user.getTotalFat() + meal.getFat());
            }
            if(meal.getCategory() == MealCategory.DINNER)
            {
                user.setDinnerKcal(user.getDinnerKcal() + meal.getKcal());
                user.setTotalCarbs(user.getTotalCarbs() + meal.getCarbs());
                user.setTotalProtein(user.getTotalProtein() + meal.getProtein());
                user.setTotalFat(user.getTotalFat() + meal.getFat());
            }
            if(meal.getCategory() == MealCategory.LUNCH)
            {
                user.setLunchKcal(user.getLunchKcal() + meal.getKcal());
                user.setTotalCarbs(user.getTotalCarbs() + meal.getCarbs());
                user.setTotalProtein(user.getTotalProtein() + meal.getProtein());
                user.setTotalFat(user.getTotalFat() + meal.getFat());
            }
            user.getEatenMeals().add(meal);
            userRepo.save(user);

            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        }catch (NullPointerException e)
        {
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }
    }


    public GeneralDTO getEatenMealsByCategory(MealCategory category , UserEntity user)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            List<Meal> meals = user.getEatenMeals().stream().filter(meal -> meal.getCategory()==category).toList();
            DTO.setDTO(meals);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        }catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }
    }
}
