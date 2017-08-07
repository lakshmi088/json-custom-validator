package com.jsonpoc;

import com.jsonpoc.component.ValidateComponent;
import com.jsonpoc.dto.ValidationStatus;

public class Client {

    private static String dataFilePath = System.getProperty("user.dir") + "/src/main/resources/data.json";
    private static String schemaFilePath = System.getProperty("user.dir") + "/src/main/resources/required_schema_20170804.json";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ValidateComponent validateComponent = new ValidateComponent();
        //Validating schema
//        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchema(schemaFilePath, dataFilePath);
//        System.out.println(validationScheamStatus);
        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchemaValidations(schemaFilePath, dataFilePath);
        System.out.println(validationScheamStatus);
    }
}
