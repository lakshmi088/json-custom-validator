package com.iris.ngc.validator;

import java.util.List;

public class EnumValidator {

    public boolean validate(String value, List<String> enumurations) {
        if (enumurations == null || enumurations.isEmpty()) {
            return false;
        }
        return enumurations.contains(value);
    }
}
