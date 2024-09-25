package com.amy.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {
    public static String getCurrentTime(String format) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return currentTime.format(formatter);

    }
}
