package com.FinalProject.Coders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String hospital;
    private String speciality;
    @ElementCollection(targetClass = DayOfWeek.class ,fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_working_days", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> workingDays;

    @OneToOne(mappedBy = "doctorInfo")
    private UserEntity user;

    @OneToMany(mappedBy = "doctor_id" , fetch = FetchType.EAGER)
    private Set<DoctorAvailability> doctorAvailabilities = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();
}
