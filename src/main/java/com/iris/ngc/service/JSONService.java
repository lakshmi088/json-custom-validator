package com.iris.ngc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import java.io.File;
import java.io.FileInputStream;

public class JSONService {

    public JsonNode getJsonNodeFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(new File(path)));
        return node;
    }

    public JsonSchema getJsonSchemaFromFile(String path) throws Exception {
        JsonSchemaFactory factory = new JsonSchemaFactory();
        JsonSchema schema = factory.getSchema(new FileInputStream(new File(path)));
        return schema;
    }
}
