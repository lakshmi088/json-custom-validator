package com.iris.ngc;

import com.iris.ngc.component.ValidateComponent;
import org.junit.Assert;
import org.junit.Test;

public class SchemaValidationTest {

    private static String testFilesRoot = System.getProperty("user.dir") + "/src/main/resources/test/";
    private static String invalidDataFilePath = testFilesRoot + "test_data_invalid.json";
    private static String validDataFilePath = testFilesRoot + "test_data_valid.json";
    private static String schemaFilePath = testFilesRoot + "test_schema.json";
    private static String dateTimeSchemaFilePath = testFilesRoot + "date_time_s.json";
    private static String dateTimeValidFilePath = testFilesRoot + "date_time_v_d.json";
    private static String dateTimeInValidFilePath = testFilesRoot + "date_time_iv_d.json";
    private static String dateTimeGTValidFilePath = testFilesRoot + "date_time_gt_v_d.json";
    private static String dateTimeGTInValidFilePath = testFilesRoot + "date_time_gt_iv_d.json";

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

    @Test
    public void dateInvalid() {
        ValidateComponent validateComponent = new ValidateComponent();
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeInValidFilePath).isSuccess(), false);
    }

    @Test
    public void dateValid() {
        ValidateComponent validateComponent = new ValidateComponent();
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeValidFilePath).isSuccess(), true);
    }
//
//    @Test
//    public void dateGtValid() {
//        ValidateComponent validateComponent = new ValidateComponent();
//        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeGTValidFilePath).isSuccess(), true);
//    }
//
//    @Test
//    public void dateGtInValid() {
//        ValidateComponent validateComponent = new ValidateComponent();
//        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeGTInValidFilePath).isSuccess(), true);
//    }

}
