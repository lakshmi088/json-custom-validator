package com.iris.ngc.validator;

import com.jayway.jsonpath.DocumentContext;
import java.util.HashSet;
import java.util.Set;

public class DefaultValueValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "defaultValue";
    private String message;
    private String defaultValue;

    public DefaultValueValidator(String defaultValue, String message) {
        this.message = message;
        this.defaultValue = defaultValue;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (value == null || value.isEmpty()) {
            errors.add(buildValidationMessage(at, "Value Required", this.VALIDATIONTYPE));
        }

        if (!value.equals(this.defaultValue)) {
            errors.add(buildValidationMessage(at, this.message, this.VALIDATIONTYPE));
        }
        return errors;
    }
}
