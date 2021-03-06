package com.iris.ngc.validation;

import com.iris.ngc.validator.CustomValidationMessage;
import com.iris.ngc.validator.DateGtValidator;
import com.iris.ngc.validator.DateLtValidator;
import com.iris.ngc.validator.DateRangeValidator;
import com.iris.ngc.validator.DefaultValueValidator;
import com.iris.ngc.validator.JsonCustomValidator;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static com.jayway.jsonpath.JsonPath.using;
import com.jayway.jsonpath.PathNotFoundException;
import java.util.HashSet;
import java.util.Set;
import net.minidev.json.JSONArray;

public class JsonValidations {

    private String jsonSchemaString;
    private String validationsProperty;
    private List<String> originalPaths;
    private ReadContext datactx;
    private Map<String, String> parentSchemaTypes;
    private Map<String, List<JsonCustomValidator>> validators;

    public JsonValidations() {
    }

    public JsonValidations(String jsonSchemaString) {
        this(jsonSchemaString, "validations");
    }

    public JsonValidations(String jsonSchemaString, String validationsProperty) {
        this.jsonSchemaString = jsonSchemaString;
        this.validationsProperty = validationsProperty;
        this.datactx = JsonPath.parse(this.jsonSchemaString);
        this.init();
    }

    public final void init() {
        this.originalPaths = new ArrayList();
        this.parentSchemaTypes = new LinkedHashMap();
        this.validators = new LinkedHashMap();
        this.read();
    }

    public void read() {
        Configuration conf;
        List<String> pathList;
        DocumentContext documentContext;
        try {
            conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
            documentContext = using(conf).parse(this.jsonSchemaString);
            pathList = documentContext.read("$.." + validationsProperty);
            for (String path : pathList) {
                originalPaths.add(path);
                this.buildValidationDeatils(path);
            }

        } catch (PathNotFoundException e) {
//            e.printStackTrace();
        }
    }

    public void buildValidationDeatils(String originalPath) {
        String nodePath, dataPath;
        Map detailsNode;
        try {
            nodePath = parseNodePath(originalPath);
            detailsNode = this.datactx.read(nodePath);
            dataPath = this.parseDataPath(nodePath);
            this.parentSchemaTypes.put(dataPath, getSchemaParentNodeType(dataPath));
            this.validators.put(dataPath, getSchemaValidators((JSONArray) detailsNode.get(this.validationsProperty)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String parseNodePath(String originalPath) {
        String nodePath = "";
        int lastIndex;
        try {
            lastIndex = originalPath.lastIndexOf("['" + this.validationsProperty + "']");
            nodePath = originalPath.substring(0, lastIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodePath;
    }

    public String parseDataPath(String nodePath) {
        String dataPath = "";
        try {
            if (nodePath.contains("['items']['properties']")) {
                dataPath = nodePath.replaceAll("\\['items']\\['properties']", "[*]").replaceAll("\\['properties']", "");
            } else {
                dataPath = nodePath.replaceAll("\\['properties']", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataPath;
    }

    public String getSchemaParentNodeType(String dataPath) {
        String type = "object";
        if (dataPath.substring(0, dataPath.lastIndexOf("['")).endsWith("[*]")) {
            type = "array";
        }
        return type;
    }

    public List<JsonCustomValidator> getSchemaValidators(JSONArray schemaValidators) {
        List<JsonCustomValidator> customValidators = new ArrayList();
        JsonCustomValidator customValidator = null;
        Map currentSchemaValidator;
        String condition, failureMessage, compareWith, format, defaultValue;
        for (int validtorInd = 0; validtorInd < schemaValidators.size(); validtorInd++) {
            currentSchemaValidator = (Map) schemaValidators.get(validtorInd);
            if (currentSchemaValidator != null && !currentSchemaValidator.isEmpty()) {
                condition = (String) currentSchemaValidator.get("condition");
                if (condition != null && !condition.isEmpty()) {
                    failureMessage = (String) currentSchemaValidator.get("failureMessage");
                    if ("defaultvalue".equalsIgnoreCase(condition)) {
                        defaultValue = (String) currentSchemaValidator.get("default");
                        customValidator = new DefaultValueValidator(defaultValue, failureMessage);
                    } else if ("lessthan".equalsIgnoreCase(condition)) {
                        compareWith = (String) currentSchemaValidator.get("compareWith");
                        customValidator = new DateLtValidator(compareWith, failureMessage);
                    } else if ("greaterthan".equalsIgnoreCase(condition)) {
                        compareWith = (String) currentSchemaValidator.get("compareWith");
                        customValidator = new DateGtValidator(compareWith, failureMessage);
                    } else if ("daterange".equalsIgnoreCase(condition)) {
                        customValidator = new DateRangeValidator((String) currentSchemaValidator.get("fromDate"), (String) currentSchemaValidator.get("toDate"), failureMessage);
                    }
                    customValidators.add(customValidator);
                }
            }
        }
        return customValidators;
    }

    public Set<CustomValidationMessage> validate(String jsonDataString) {
        Configuration conf = Configuration.builder()
                .options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
        DocumentContext dataCtxt = using(conf).parse(jsonDataString);
        Set<CustomValidationMessage> errors = new HashSet();
        List<String> valuesList;
        String dataPath, value;
        Object valueObj;
        for (Map.Entry<String, List<JsonCustomValidator>> validators : validators.entrySet()) {
            dataPath = validators.getKey();
            if ("object".equalsIgnoreCase(this.parentSchemaTypes.get(dataPath))) {
                for (JsonCustomValidator validator : validators.getValue()) {
                    valueObj = dataCtxt.read(dataPath);
                    value = valueObj == null ? null : String.valueOf(valueObj);
                    errors.addAll(validator.validate(value, dataCtxt, dataPath));
                }
            } else {
                //TODO for array
                valuesList = dataCtxt.read(dataPath);
                for (int valInd = 0; valInd < valuesList.size(); valInd++) {
                    for (JsonCustomValidator validator : validators.getValue()) {
                        errors.addAll(validator.validate(valuesList.get(valInd), dataCtxt, dataPath.replace("*", String.valueOf(valInd))));
                    }
                }
            }

        }
        return errors;
    }
}
