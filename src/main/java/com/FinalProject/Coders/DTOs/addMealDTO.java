package com.FinalProject.Coders.DTOs;

import com.FinalProject.Coders.Enums.MealCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class addMealDTO {
    private String mealName;
    private Integer kcal;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private MealCategory category;

}
