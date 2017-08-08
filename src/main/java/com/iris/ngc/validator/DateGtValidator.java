package com.iris.ngc.validator;

import com.iris.ngc.util.AppliationUtil;
import com.jayway.jsonpath.DocumentContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateGtValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "dateGreaterThan";
    private String message;
    private String comparePath;

    public DateGtValidator(String comparePath, String message) {
        this.comparePath = comparePath;
        this.message = message;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (value == null || value.isEmpty()) {
            errors.add(buildValidationMessage(at, "Value required", this.VALIDATIONTYPE));
        }
        String compareWith = dataCtxt.read(this.comparePath);

        if (compareWith == null || compareWith.isEmpty()) {
            errors.add(buildValidationMessage(at, "Compare value required", this.VALIDATIONTYPE));
        }
        Date dateValue = AppliationUtil.parseDate(value);
        Date compareDate = AppliationUtil.parseDate(compareWith);

        if (dateValue == null || compareDate == null) {
            errors.add(buildValidationMessage(at, "Date formats not valid", this.VALIDATIONTYPE));
        } else {
            if(!(compareDate.compareTo(dateValue) < 0)) {
                errors.add(buildValidationMessage(at, this.message, this.VALIDATIONTYPE));
            }
        }

        return errors;
    }
}
