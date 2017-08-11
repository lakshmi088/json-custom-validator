package com.iris.ngc;

import com.iris.ngc.component.ValidateComponent;
import org.junit.Assert;
import org.junit.Test;

public class SchemaValidationTest {

    private static String testFilesRoot = System.getProperty("user.dir") + "/src/main/resources/test/";
    private static String invalidDataFilePath = testFilesRoot + "test_data_invalid.json";
    private static String validDataFilePath = testFilesRoot + "test_data_valid.json";
    private static String schemaFilePath = testFilesRoot + "test_schema.json";

    /*Date validations starts*/
    private static String dateTimeSchemaFilePath = testFilesRoot + "date_time_s.json";
    private static String dateTimeValidFilePath = testFilesRoot + "date_time_v_d.json";
    private static String dateTimeInValidFilePath = testFilesRoot + "date_time_iv_d.json";
    private static String dateTimeGTSchemaFilePath = testFilesRoot + "date_time_gt_s.json";
    private static String dateTimeLTSchemaFilePath = testFilesRoot + "date_time_lt_s.json";
    private static String dateTimeGTValidFilePath = testFilesRoot + "date_time_gt_v_d.json";
    private static String dateTimeGTInValidFilePath = testFilesRoot + "date_time_gt_iv_d.json";
    private static String dateTimeRangeSchemaFilePath = testFilesRoot + "date_time_range_s.json";
    private static String dateTimeRangeValidFilePath = testFilesRoot + "date_time_range_v_d.json";
    private static String dateTimeRangeInValidFilePath = testFilesRoot + "date_time_range_iv_d.json";


    /*Enum validations starts*/
    private static String enumSchemaFilePath = testFilesRoot + "enum_s.json";
    private static String enumValidFilePath = testFilesRoot + "enum_v_d.json";
    private static String enumInValidFilePath = testFilesRoot + "enum_iv_d.json";


    /*Default validations starts*/
    private static String defaultSchemaFilePath = testFilesRoot + "default_s.json";
    private static String defaultValidFilePath = testFilesRoot + "default_v_d.json";
    private static String defaultInValidFilePath = testFilesRoot + "default_iv_d.json";

    /*Dependency validations starts*/
    private static String dependencySchemaFilePath = testFilesRoot + "dependency_s.json";
    private static String dependencyValidFilePath = testFilesRoot + "dependency_v_d.json";
    private static String dependencyInValidFilePath = testFilesRoot + "dependency_iv_d.json";

    ValidateComponent validateComponent = new ValidateComponent();

    @Test
    public void testInvalidSchema() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(schemaFilePath, invalidDataFilePath).isSuccess(), false);

    }

    @Test
    public void testValidSchema() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(schemaFilePath, validDataFilePath).isSuccess(), true);

    }

    /*Date Validations starts*/
    @Test
    public void dateInvalid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeInValidFilePath).isSuccess(), false);
    }

    @Test
    public void dateValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeSchemaFilePath, dateTimeValidFilePath).isSuccess(), true);
    }

    @Test
    public void dateGtValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeGTSchemaFilePath, dateTimeGTValidFilePath).isSuccess(), true);
    }

    @Test
    public void dateGtInValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeGTSchemaFilePath, dateTimeGTInValidFilePath).isSuccess(), false);
    }

    @Test
    public void dateLtValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeLTSchemaFilePath, dateTimeGTValidFilePath).isSuccess(), true);
    }

    @Test
    public void dateLtInValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeLTSchemaFilePath, dateTimeGTInValidFilePath).isSuccess(), false);
    }

    @Test
    public void dateRangeValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeRangeSchemaFilePath, dateTimeRangeValidFilePath).isSuccess(), true);
    }

    @Test
    public void dateRangeInValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(dateTimeLTSchemaFilePath, dateTimeRangeInValidFilePath).isSuccess(), false);
    }

    /*Enum Validations starts*/
    @Test
    public void enumValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(enumSchemaFilePath, enumValidFilePath).isSuccess(), true);
    }

    @Test
    public void enumInValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(enumSchemaFilePath, enumInValidFilePath).isSuccess(), false);
    }

    /*Default Validations starts*/
    @Test
    public void defulatValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(defaultSchemaFilePath, defaultValidFilePath).isSuccess(), true);
    }

    @Test
    public void defulatInValid() {
        Assert.assertEquals(validateComponent.validateDataWithSchema(defaultSchemaFilePath, defaultInValidFilePath).isSuccess(), false);
    }

    /*Dependency Validations starts*/
//    @Test
//    public void dependencyValid() {
//        Assert.assertEquals(validateComponent.validateDataWithSchema(dependencySchemaFilePath, dependencyValidFilePath).isSuccess(), true);
//    }
//
//    @Test
//    public void dependencyInValid() {
//        Assert.assertEquals(validateComponent.validateDataWithSchema(dependencySchemaFilePath, dependencyInValidFilePath).isSuccess(), false);
//    }
}
