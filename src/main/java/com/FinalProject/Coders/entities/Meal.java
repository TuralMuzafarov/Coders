package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.Enums.MealCategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealName;
    private Integer kcal;
    @Enumerated(EnumType.STRING)
    private MealCategory category;
    private Integer protein;
    private Integer carbs;
    private Integer fat;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attachment_id" , referencedColumnName = "id")
    private Attachment attachment;


    @ManyToMany(mappedBy = "eatenMeals")
    private List<UserEntity> user;
}
