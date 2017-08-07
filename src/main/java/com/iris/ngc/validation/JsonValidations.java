package com.iris.ngc.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class JsonValidations {

    private String jsonSchemaString;
    private String validationsProperty;
    private List<String> originalPaths;
    private ReadContext datactx;
    private Map<String, String> originalPathsRef;
    private Map<String, Object> validationRequiredPaths;
    private ObjectMapper mapper;

    public JsonValidations() {
    }

    public JsonValidations(String jsonSchemaString) {
        this(jsonSchemaString, "validations");
    }

    public JsonValidations(String jsonSchemaString, String validationsProperty) {
        this.jsonSchemaString = jsonSchemaString;
        this.validationsProperty = validationsProperty;
        this.mapper = new ObjectMapper();
        this.datactx = JsonPath.parse(this.jsonSchemaString);
        this.init();
    }

    public final void init() {
        try {
            this.originalPaths = new ArrayList();
            this.validationRequiredPaths = new LinkedHashMap();
            this.originalPathsRef = new LinkedHashMap();
            this.read();
        } catch (Exception e) {
        }
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildValidationDeatils(String originalPath) {
        String nodePath, dataPath;
        Map detailsNode;
        try {
            nodePath = parseNodePath(originalPath);
            detailsNode = this.datactx.read(nodePath);
            dataPath = this.parseDataPath(nodePath);
            this.originalPathsRef.put(originalPath, dataPath);
            this.validationRequiredPaths.put(dataPath, detailsNode);
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

    public Object validate(String jsonDataString) {
        Configuration conf = Configuration.builder()
                .options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
        DocumentContext dataCtxt = using(conf).parse(jsonDataString);
        for (Map.Entry<String, Object> dataPath : this.validationRequiredPaths.entrySet()) {
            System.out.println(dataPath.getKey() + " = " + dataCtxt.read(dataPath.getKey()).toString());
        }
//        System.out.println(JsonPath.read(jsonDataString, "$['content']['offerEndDate']").toString());
//        System.out.println(JsonPath.read(jsonDataString, "$['content']['offers'][*]").toString());
//        System.out.println(JsonPath.read(jsonDataString, "$['content']['offers'][*]['headLine']").toString());
//        System.out.println(using(conf).parse(jsonDataString).read("$['content']['offers'][*]['headLine']").toString());
        return null;
    }
}
