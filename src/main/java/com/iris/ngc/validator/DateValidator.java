package com.iris.ngc.validator;

import com.iris.ngc.util.AppliationUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    public boolean validate(String value, String dateFromat) {
        boolean valid = true;
        if (value == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {
            sdf.parse(value);
        } catch (ParseException e) {
            valid = false;
        }
        return valid;
    }

    public boolean compare(String start, String end) {
        boolean valid = true;
        Date startDate;
        Date endDate;
        if (start == null || end == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(AppliationUtil.DEFAULTDATEFORMAT);
        sdf.setLenient(false);
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
            valid = endDate.compareTo(startDate) > 0;
        } catch (ParseException e) {
            valid = false;
        }
        return valid;
    }

}
