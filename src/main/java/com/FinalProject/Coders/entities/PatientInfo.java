package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.Enums.Blood;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "patient_info")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Blood blood;

    private Integer height;

    private Integer weight;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "current_diseases" , joinColumns = @JoinColumn(name = "current_diseases_id"))
    private List<String> currentDiseases;

    private Boolean isTakingMedication;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Integer currentStepCount;

    private Integer caloryburned;

    private Integer desiredKg;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

}
