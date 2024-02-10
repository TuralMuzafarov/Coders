package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.*;
import com.FinalProject.Coders.entities.*;
import com.FinalProject.Coders.repositories.*;
import com.sun.source.tree.LabeledStatementTree;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.asm.tree.TryCatchBlockNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional()
    public GeneralDTO addTreatment(Long id, addTreatmentDTO req)
    {
        GeneralDTO DTO = new GeneralDTO();
        try {
            Appointment appointment = appointmentRepo.findById(id).orElseThrow(EntityNotFoundException::new);

            Treatment treatment = Treatment.builder()
                    .diagnose(req.getDiagnose())
                    .status(req.getStatus())
                    .needAnalyze(req.getNeedAnalyze())
                    .build();

            appointment.setTreatment(treatment);

            List<Medicine> medicineList = req.getMedicineList().stream().map(medDto->
            {
                Medicine medicine = Medicine.builder()
                        .medicineName(medDto.getMedicineName())
                        .dose(medDto.getDose())
                        .postScript(medDto.getPostScript())
                        .takenDays(medDto.getTakenDays())
                        .treatment(treatment)
                        .build();
                return medicine;
            }).toList();
            treatment.setMedicineList(medicineList);
            appointmentRepo.save(appointment);
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        } catch (Exception e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }
    }


    public GeneralDTO getLastExaminations(UserEntity user, PaginationRequestDTO pageReq)
    {
        Pageable pageable = pageReq.getPageable(pageReq);
        GeneralDTO DTO = new GeneralDTO();
        try {
            Set<Appointment> lastAppointments= user.getAppointments();
            List<LastExaminationDTO> examinationDTOS = lastAppointments.stream().map((examination)->
                    {
                        return LastExaminationDTO
                                .builder()
                                .appointmentDate(examination.getAppointmentTime().toLocalDate().toString())
                                .diagnose(examination.getTreatment().getDiagnose())
                                .status(examination.getTreatment().getStatus())
                                .needAnalyze(examination.getTreatment().getNeedAnalyze())
                                .build();
                    }
            ).toList();

            Page<LastExaminationDTO> page = createPage(examinationDTOS , pageReq.getPageSize() , pageable);
            DTO.setDTO(new PageImpl<>(examinationDTOS, pageable, page.getTotalElements()));
            DTO.setStatusCode(HttpStatus.ACCEPTED);
            return DTO;
        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            DTO.setStatusCode(HttpStatus.BAD_REQUEST);
            return DTO;
        }
    }

    public static <T> Page<T> createPage(List<T> items, int pageSize , Pageable pageable) {
        int totalItems = items.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int start = 0;
        int end = Math.min(totalItems, pageSize);

        List<T> sublist = items.subList(start, end);

        return new PageImpl<>(sublist, pageable , totalItems);
    }


}
