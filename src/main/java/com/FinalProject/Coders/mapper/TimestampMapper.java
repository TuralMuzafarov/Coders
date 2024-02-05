package com.FinalProject.Coders.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampMapper {
    public static LocalDate convertTimestampToLocalDate(long timestamp) {
        // Convert timestamp to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());

        // Extract LocalDate from LocalDateTime
        LocalDate localDate = dateTime.toLocalDate();

        return localDate;
    }
}
