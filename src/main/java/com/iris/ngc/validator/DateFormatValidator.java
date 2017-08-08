package com.iris.ngc.validator;

import com.iris.ngc.util.AppliationUtil;
import com.jayway.jsonpath.DocumentContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateFormatValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "dateFormat";
    private String message;
    private String format;

    public DateFormatValidator(String format, String message) {
        this.format = format;
        this.message = message;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (value == null || value.isEmpty()) {
            errors.add(buildValidationMessage(at, "Value required", this.VALIDATIONTYPE));
        }
        Date dateValue = AppliationUtil.parseDate(value);
        if (dateValue == null) {
            errors.add(buildValidationMessage(at, this.message, this.VALIDATIONTYPE));
        }
        return errors;

    }
}
