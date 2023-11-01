package com.unidac.tools.utils;

import com.unidac.tools.dto.FilterDTO;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {

     public LocalDateTime toLocalDateTime(String dateToConvert) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime
                .parse(dateToConvert, formatter)
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))
                .toLocalDateTime();
    }

    public LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
       return localDateTime.toLocalDate();
    }

    public LocalDate toLocalDate(String date){
        return convertLocalDateTimeToLocalDate(toLocalDateTime(date));
    }


    public Instant hourToInstant(int hour){
        return LocalDateTime.now().plusHours(hour).toInstant(ZoneOffset.ofHours(-3));
    }

}
