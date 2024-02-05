package com.FinalProject.Coders.services;

import com.FinalProject.Coders.entities.DoctorAvailability;
import com.FinalProject.Coders.entities.DoctorInfo;
import com.FinalProject.Coders.repositories.DoctorAvailabilityRepo;
import com.FinalProject.Coders.repositories.DoctorInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {
    private final DoctorInfoRepo doctorInfoRepo;
    private final DoctorAvailabilityRepo doctorAvailabilityRepo;
    private static final int WORKING_HOURS = 6; // Assuming doctor works 6 hours in a day
    private static final LocalTime START_TIME = LocalTime.of(10, 0); // Start time for appointments

    public List<LocalTime> calculateAvailableTimes(List<Boolean> availableHours) {
        List<LocalTime> availableTimes = new ArrayList<>();

        for (int i = 0; i < availableHours.size(); i++) {
            if (!availableHours.get(i)) {
                LocalTime appointmentTime = START_TIME.plusHours(i);
                availableTimes.add(appointmentTime);
            }
        }

        return availableTimes;
    }

    public Integer calculateAvailabilityIndex(LocalTime time) {
        Integer index = time.getHour() - START_TIME.getHour();
        return index;
    }

    public List<String> getAvailableTimes(String name , String surname , String speciality, LocalDate selectedDate) {
        DoctorInfo doctorInfo = doctorInfoRepo.findDoctorInfoByNameAndSurnameAndSpeciality(name , surname , speciality).orElse(null);
        if (doctorInfo == null) {
            return Collections.emptyList();
        }

        DoctorAvailability doctorAvailability = doctorAvailabilityRepo.findDoctorAvailabilitiesByDate(selectedDate).orElse(null);

        if(doctorAvailability != null)
        {
            List<Boolean> availableHours = doctorAvailability.getAvailableHours();

            List<LocalTime> vacantTimes = calculateAvailableTimes(availableHours);

            List<String> vacantTimesString = vacantTimes.stream().map(Objects::toString).toList();

            return vacantTimesString;
        }
        else{
            return Collections.emptyList();
        }

    }


}
