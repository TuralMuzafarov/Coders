package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.entities.Attachment;
import com.FinalProject.Coders.entities.Meal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMealDTO {
    private Long mealId;
    private String mealName;
    private Integer kcal;
    private String base64Photo;
}
