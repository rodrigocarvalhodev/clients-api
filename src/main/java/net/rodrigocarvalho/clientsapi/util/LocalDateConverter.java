package net.rodrigocarvalho.clientsapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {

    public LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
