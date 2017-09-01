package com.iris.ngc;

import com.iris.ngc.component.ValidateComponent;
import com.iris.ngc.dto.ValidationStatus;

public class Client {

    private static String dataFilePath = System.getProperty("user.dir") + "/src/main/resources/data.json";
    private static String schemaFilePath = System.getProperty("user.dir") + "/src/main/resources/required_schema_20170809.json";

    public static void main(String[] args) {
        ValidateComponent validateComponent = new ValidateComponent();
        //Validating schema
        ValidationStatus validationScheamStatus = validateComponent.validateDataWithSchema(schemaFilePath, dataFilePath);
        System.out.println(validationScheamStatus);

    }
}
