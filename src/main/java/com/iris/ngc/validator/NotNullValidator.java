package com.iris.ngc.validator;

import com.jayway.jsonpath.DocumentContext;
import java.util.HashSet;
import java.util.Set;

public class NotNullValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "notNull";
    private String message;

    public NotNullValidator(String message) {
        this.message = message;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (value == null || value.isEmpty()) {
            errors.add(buildValidationMessage(at, this.message, this.VALIDATIONTYPE));
        }
        return errors;
    }
}
