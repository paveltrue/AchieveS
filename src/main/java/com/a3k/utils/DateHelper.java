package com.a3k.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

public class DateHelper {


    /**
     * Used for various date verifications
     * @param format you want verify. i.e. "dd/MM/yyyy"
     * @param value i.e. "25/09/2013" or "25/09/2013  12:13:50"
     * @return true/false
     */
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        value = value.replaceAll("\\s+", " ");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    /*
     * LocalDate is newer, Date and SimpleDateFormatter are deprecated
     */
    public static boolean isValidDateFormat(String format, String value) {
        LocalDate date = null;
        value = value.replaceAll("\\s+", " ");

        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(format)
                .parseDefaulting(ChronoField.YEAR, Year.now().getValue() - 1)
                .toFormatter();
        try {
            date = LocalDate.parse(value, dtf);
            if (!value.equals(date.format(DateTimeFormatter.ofPattern(format)))) {
                date = null;
            }
        } catch (DateTimeParseException dtpe) {
            dtpe.printStackTrace();
        }
        return date != null;
    }

    public static boolean isValidFormat(String format, String value, Locale locale) {
        Date date = null;
        value = value.replaceAll("\\s+", " ");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    public static boolean isValidFormatSpanish(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale
                    ("es", "ES"));
            sdf.setLenient(false);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

}
