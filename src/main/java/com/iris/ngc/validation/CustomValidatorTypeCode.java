package com.iris.ngc.validation;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public enum CustomValidatorTypeCode {
    REQUIRED("required", "1028", new MessageFormat("{0}.{1}: is missing but it is required")),
    ENUM("enum", "1008", new MessageFormat("{0}: does not have a value in the enumeration {1}")),
    UNIQUE_ITEMS("uniqueItems", "1031", new MessageFormat("{0}: the items in the array must be unique"));

    private static Map<String, CustomValidatorTypeCode> constants = new HashMap<String, CustomValidatorTypeCode>();

    static {
        for (CustomValidatorTypeCode c : values()) {
            constants.put(c.value, c);
        }
    }

    private final String value;
    private final String errorCode;
    private final MessageFormat messageFormat;
    private final String errorCodeKey;

    private CustomValidatorTypeCode(String value, String errorCode, MessageFormat messageFormat) {
        this.value = value;
        this.errorCode = errorCode;
        this.messageFormat = messageFormat;
        this.errorCodeKey = value + "ErrorCode";
    }

    public static CustomValidatorTypeCode fromValue(String value) {
        CustomValidatorTypeCode constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    public String getErrorCodeKey() {
        return errorCodeKey;
    }

}
