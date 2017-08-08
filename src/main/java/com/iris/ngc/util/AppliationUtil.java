package com.iris.ngc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppliationUtil {

    public static final String DEFAULTDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static Date parseDate(String value) {
        return parseDate(value, DEFAULTDATEFORMAT);
    }

    public static Date parseDate(String value, String format) {
        Date date = null;
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
        }
        return date;
    }
}
