package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.Enums.ExaminationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id" , referencedColumnName = "id")
    private  Appointment appointment;

    @OneToMany(mappedBy = "treatment" , cascade = CascadeType.ALL)

    private List<Medicine> medicineList = new ArrayList<>();

    private String diagnose;
    private Boolean needAnalyze;

    @Enumerated(EnumType.STRING)
    private ExaminationStatus status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
