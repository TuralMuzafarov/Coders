package com.FinalProject.Coders.entities;

import com.FinalProject.Coders.DTOs.DTO;
import com.FinalProject.Coders.DTOs.DoctorDTO;
import com.FinalProject.Coders.Enums.Gender;
import com.FinalProject.Coders.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserEntity implements UserDetails , DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;
    private String firstName;

    private String lastName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true , length = 100 , nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "attachment_id" , referencedColumnName = "id")
    private Attachment attachment;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private PatientInfo patientInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id" , referencedColumnName = "id")
    private DoctorInfo doctorInfo;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private Set<Appointment> appointments;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "meals_eaten",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Meal> eatenMeals = new ArrayList<>();

    private Integer breakfastKcal = 0;

    private Integer dinnerKcal = 0;

    private Integer lunchKcal = 0;
    private Integer totalProtein = 0;
    private Integer totalCarbs = 0;
    private Integer totalFat = 0;

    public UserEntity() {
        this.setBreakfastKcal(0);
        this.setLunchKcal(0);
        this.setDinnerKcal(0);
        this.totalFat = 0;
        this.totalCarbs = 0;
        this.totalProtein = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
