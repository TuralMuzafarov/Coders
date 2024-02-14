package com.FinalProject.Coders.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.mapping.Map;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection(targetClass = Boolean.class)
    @CollectionTable(name = "doctor_available_hours" , joinColumns = @JoinColumn(name = "doctor_id"))
    @Fetch(FetchMode.JOIN)
    private List<Boolean> availableHours = List.of(false , false , false , false , false , false);

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorInfo doctor_id;

    public DoctorAvailability(LocalDate date) {
        this.date = date;
        this.availableHours = new ArrayList<>(List.of(false, false, false, false, false, false));
    }
}
