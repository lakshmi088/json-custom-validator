package com.jsonpoc.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonpoc.dto.ValidationStatus;
import com.jsonpoc.service.JSONService;
import com.jsonpoc.validation.JsonValidations;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import java.util.Set;

public class ValidateComponent {

    JSONService jsonService = new JSONService();

    public ValidationStatus validateDataWithSchema(String schemaFilePath, String dataFilePath) {
        ValidationStatus validationStatus = new ValidationStatus();
        try {
            JsonSchema schema = jsonService.getJsonSchemaFromFile(schemaFilePath);
            JsonNode node = jsonService.getJsonNodeFromFile(dataFilePath);
            Set<ValidationMessage> errors = schema.validate(node);
            if (errors.isEmpty()) {
                validationStatus.setSuccess(true);
                validationStatus.setMessage("Schema and data matched successfully");
            } else {
                validationStatus.setSuccess(false);
                validationStatus.setMessage("Errors (" + errors.size() + "): " + errors.toString());
            }
        } catch (Exception e) {
            validationStatus.setSuccess(false);
            validationStatus.setMessage("Exception: " + e.getMessage());
        }
        return validationStatus;
    }

    public ValidationStatus validateDataWithSchemaValidations(String schemaFilePath, String dataFilePath) {
        ValidationStatus validationStatus = new ValidationStatus();
        try {
            String jsonSchemaAsString = jsonService.getJsonNodeFromFile(schemaFilePath).toString();
            JsonValidations jsonValidations = new JsonValidations(jsonSchemaAsString);
            JsonNode dataNode = jsonService.getJsonNodeFromFile(dataFilePath);
            jsonValidations.validate(dataNode.toString());
        } catch (Exception e) {
            validationStatus.setSuccess(false);
            validationStatus.setMessage("Exception: " + e.getMessage());
        }
        return validationStatus;
    }
}
