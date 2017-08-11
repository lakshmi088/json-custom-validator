package com.iris.ngc.validator;

import com.iris.ngc.util.AppliationUtil;
import com.jayway.jsonpath.DocumentContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateRangeValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "dateRangeValidation";
    private String message;
    private String fromDateStr;
    private String toDateStr;

    public DateRangeValidator(String fromDateStr, String toDateStr, String message) {
        this.fromDateStr = fromDateStr;
        this.toDateStr = toDateStr;
        this.message = message;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (value == null || value.isEmpty()) {
            errors.add(buildValidationMessage(at, "Value required", this.VALIDATIONTYPE));
        }

        if (fromDateStr == null || fromDateStr.isEmpty() || toDateStr == null || toDateStr.isEmpty()) {
            errors.add(buildValidationMessage(at, "Compare value(s) required", this.VALIDATIONTYPE));
        }
        Date dateValue = AppliationUtil.parseDate(value);
        Date fromDate = AppliationUtil.parseDate(fromDateStr);
        Date toDate = AppliationUtil.parseDate(toDateStr);

        if (dateValue == null || fromDate == null || toDate == null) {
            errors.add(buildValidationMessage(at, "Date formats not valid", this.VALIDATIONTYPE));
        } else if (dateValue.before(fromDate) || dateValue.after(toDate)) {
            errors.add(buildValidationMessage(at, this.message, this.VALIDATIONTYPE));
        }

        return errors;
    }
}
