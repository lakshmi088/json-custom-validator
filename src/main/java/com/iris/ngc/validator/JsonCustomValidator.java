package com.iris.ngc.validator;

import com.jayway.jsonpath.DocumentContext;
import java.util.Set;

public interface JsonCustomValidator {

    public static final String AT_ROOT = "$";

    Set<CustomValidationMessage> validate(String value, DocumentContext dataCtxt, String at);

}
