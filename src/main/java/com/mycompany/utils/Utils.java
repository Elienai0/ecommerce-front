package com.mycompany.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Devuelve la fecha actual en formato dd-MM-yyyy
    public static String obtenerFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    // Calcula el número de días entre dos fechas
public static int calcularDiasDeEstancia(Timestamp startDateTime, Timestamp endDateTime) {
    long diferenciaEnMilisegundos = endDateTime.getTime() - startDateTime.getTime();
    return (int) (diferenciaEnMilisegundos / (1000 * 60 * 60 * 24)); // Convertir milisegundos a días
}

    // Verifica si una fecha cumple con las horas de check-in/check-out
    public static boolean verificarHorarioCheckInOut(Date fecha, boolean esCheckIn) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hora = sdf.format(fecha);
        int horaInt = Integer.parseInt(hora);

        if (esCheckIn) {
            return horaInt >= 14;
        } else {
            return horaInt <= 12;
        }
    }

    // Combina una fecha y una hora en un objeto Date
public static Date combineDateAndTime(Date date, String time) {
    if (date == null || time == null || time.isEmpty()) {
        throw new IllegalArgumentException("La fecha y la hora no deben ser nulas o vacías.");
    }

    try {
        // Crear un formato para la fecha completa con hora
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Crear un formato para obtener solo la fecha (sin hora)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        // Combinar la fecha y la hora en una cadena
        String dateTimeString = dateString + " " + time + ":00";

        // Parsear la cadena combinada a un objeto Date
        return dateTimeFormat.parse(dateTimeString);
    } catch (ParseException e) {
        throw new IllegalArgumentException("Error al combinar fecha y hora: " + e.getMessage());
    }
}
public static String dateToString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    return formatter.format(date);
}
    public static String timestampToDateString(Timestamp timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
    }

    public static String timestampToTimeString(Timestamp timestamp) {
        return new SimpleDateFormat("HH:mm").format(timestamp);
    }
    
    public static Timestamp stringToTimestamp(String date, String time) {
    try {
        String dateTimeStr = date + " " + time + ":00";
        return Timestamp.valueOf(dateTimeStr);
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Error al convertir la fecha y hora: " + e.getMessage());
    }
}
}
