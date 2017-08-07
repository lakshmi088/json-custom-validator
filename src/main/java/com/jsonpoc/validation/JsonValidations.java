package com.jsonpoc.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import static com.jayway.jsonpath.JsonPath.using;
import com.jayway.jsonpath.Option;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonValidations {

    private String jsonSchemaString;
    private String validationsProperty;
    private Map<String, JsonNode> originalPaths;
    private ObjectMapper mapper;

    public JsonValidations() {
    }

    public JsonValidations(String jsonSchemaString) {
        this.jsonSchemaString = jsonSchemaString;
        this.validationsProperty = "validations";
        this.mapper = new ObjectMapper();
        this.init();
    }

    public JsonValidations(String jsonSchemaString, String validationsProperty) {
        this.jsonSchemaString = jsonSchemaString;
        this.validationsProperty = validationsProperty;
        this.mapper = new ObjectMapper();
    }

    public final void init() {
        try {
            this.originalPaths = new LinkedHashMap();
            this.read();
        } catch (Exception e) {
        }
    }

    public void read() {
        Configuration conf;
        List<String> pathList;
        DocumentContext documentContext;
        JsonNode validationsNode;
        try {
            conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
            documentContext = using(conf).parse(this.jsonSchemaString);
            pathList = documentContext.read("$.." + validationsProperty);

            for (String path : pathList) {
                validationsNode = this.mapper.readTree(JsonPath.read(this.jsonSchemaString, path).toString());
                originalPaths.put(path, validationsNode);
            }
            System.out.println(originalPaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildPath() {
        Configuration conf;
        List<String> pathList;
        DocumentContext documentContext;
        JsonNode validationsNode;
        try {
            conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
            documentContext = using(conf).parse(this.jsonSchemaString);
            pathList = documentContext.read("$.." + validationsProperty);

            for (String path : pathList) {
                validationsNode = this.mapper.readTree(JsonPath.read(this.jsonSchemaString, path).toString());
                originalPaths.put(path, validationsNode);
            }
            System.out.println(originalPaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object validate(String jsonDataString) {
        return null;
    }
}
