package com.iris.ngc.validator;

import com.jayway.jsonpath.DocumentContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumValidator extends BaseJsonCustomValidator implements JsonCustomValidator {

    private final String VALIDATIONTYPE = "enum";
    private List<String> enumurations;
    private String message;

    public EnumValidator(List<String> enumurations, String message) {
        this.enumurations = enumurations;
        this.message = message;
    }

    @Override
    public Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at) {
        Set<CustomValidationMessage> errors = new HashSet();
        if (this.enumurations == null || this.enumurations.isEmpty()) {
            errors.add(buildValidationMessage(at, "No enumerations defined", this.VALIDATIONTYPE));
        }
        if (!this.enumurations.contains(value)) {
            errors.add(buildValidationMessage(at, message, this.VALIDATIONTYPE));
        }

        return errors;
    }
}
