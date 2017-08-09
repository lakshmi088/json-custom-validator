package com.iris.ngc;

import com.iris.ngc.component.ValidateComponent;
import org.junit.Assert;
import org.junit.Test;

public class SchemaValidationTest {

    private static String invalidDataFilePath = System.getProperty("user.dir") + "/src/main/resources/test_data_invalid.json";
    private static String validDataFilePath = System.getProperty("user.dir") + "/src/main/resources/test_data_valid.json";
    private static String schemaFilePath = System.getProperty("user.dir") + "/src/main/resources/test_schema.json";

    @Test
    public void testInvalidSchema() {
        ValidateComponent validateComponent = new ValidateComponent();
        Assert.assertEquals(validateComponent.validateDataWithSchema(schemaFilePath, invalidDataFilePath).isSuccess(), false);

    }

    @Test
    public void testValidSchema() {
        ValidateComponent validateComponent = new ValidateComponent();
        Assert.assertEquals(validateComponent.validateDataWithSchema(schemaFilePath, validDataFilePath).isSuccess(), true);

    }

}
