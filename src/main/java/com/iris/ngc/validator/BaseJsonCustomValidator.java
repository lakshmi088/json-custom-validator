package com.iris.ngc.validator;

public abstract class BaseJsonCustomValidator implements JsonCustomValidator {

    protected CustomValidationMessage buildValidationMessage(String at, String message, String type) {
        CustomValidationMessage builder = new CustomValidationMessage();

        builder.setPath(at);
        builder.setMessage(message);
        builder.setType(type);

        return builder;
    }
}
