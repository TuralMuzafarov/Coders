package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.AppointmentDTO;
import com.FinalProject.Coders.DTOs.AppointmentRegisterDTO;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.entities.Appointment;
import com.FinalProject.Coders.entities.DoctorAvailability;
import com.FinalProject.Coders.entities.DoctorInfo;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.repositories.AppointmentRepo;
import com.FinalProject.Coders.repositories.DoctorAvailabilityRepo;
import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.asm.tree.TryCatchBlockNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final UserRepo userRepo;
    private final DoctorInfoRepo doctorInfoRepo;
    private final DoctorAvailabilityRepo doctorAvailabilityRepo;
    private final DoctorAvailabilityService doctorAvailabilityService;

    @Transactional
    public GeneralDTO registerAppointment(AppointmentRegisterDTO req , UserEntity user1)
    {
        GeneralDTO DTO = new GeneralDTO();
        Appointment appointment = new Appointment();
        try {
            UserEntity user = userRepo.findById(user1.getId()).get();
            DoctorInfo doctor;
            doctor = doctorInfoRepo.findById(req.getDoctorId()).orElseThrow(()->new RuntimeException("Doctor Not Found"));
            Set<DoctorAvailability> availabilities = doctor.getDoctorAvailabilities();
            LocalDate date = LocalDate.parse(req.getDate());
            DoctorAvailability doctorAvailability = availabilities.stream()
                    .filter(doctorAvailability1 -> doctorAvailability1.getDate().equals(date))
                    .findFirst()
                    .orElse(DoctorAvailability.builder()
                            .doctor_id(doctor)
                            .date(date).availableHours(Arrays.asList(false, false, false, false, false, false))
                    .build());

            availabilities.remove(doctorAvailability);

            LocalTime time = LocalTime.parse(req.getTime());
            doctorAvailability.getAvailableHours().set(doctorAvailabilityService.calculateAvailabilityIndex(time), true);
            availabilities.add(doctorAvailability);
            doctor.setDoctorAvailabilities(availabilities);
            appointment.setAppointmentTime(LocalDateTime.of(date , time));
            appointment.setUser(user);
            appointment.setDoctor(doctor);
            user.getAppointments().add(appointment);
            doctor.getAppointments().add(appointment);

            appointmentRepo.save(appointment);
            doctorAvailabilityRepo.save(doctorAvailability);
            doctorInfoRepo.save(doctor);
            userRepo.save(user);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;

        } catch (RuntimeException e) {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }

    }


}
