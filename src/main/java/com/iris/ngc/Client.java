package com.iris.ngc;

import com.iris.ngc.component.ValidateComponent;
import com.iris.ngc.dto.ValidationStatus;

public class Client {

    private static String dataFilePath = System.getProperty("user.dir") + "/src/main/resources/test_data_valid.json";
    private static String schemaFilePath = System.getProperty("user.dir") + "/src/main/resources/test_schema.json";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ValidateComponent validateComponent = new ValidateComponent();
        //Validating schema
        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchema(schemaFilePath, dataFilePath);
        System.out.println(validationScheamStatus);
//        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchemaValidations(schemaFilePath, dataFilePath);
//        System.out.println(validationScheamStatus);
    }
}
