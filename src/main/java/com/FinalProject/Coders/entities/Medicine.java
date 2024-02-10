package com.FinalProject.Coders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private String dose;

    @ElementCollection(targetClass = String.class ,fetch = FetchType.EAGER)
    @CollectionTable(name = "medicine_taken_days", joinColumns = @JoinColumn(name = "medicineId"))
    @Enumerated(EnumType.STRING)
    private List<String> takenDays;
    private String postScript;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treatment_id" , referencedColumnName = "id")
    private Treatment treatment;
}
