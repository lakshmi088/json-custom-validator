package com.jsonpoc.validation;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import static com.jayway.jsonpath.JsonPath.using;
import com.jayway.jsonpath.Option;
import java.util.List;

public class JsonValidations {

    private final static String json = "{ \"$schema\" : \"http://json-schema.org/draft-04/schema#\", \"definitions\" : {}, \"properties\" : { \"active\" : { \"type\" : \"boolean\" }, \"content\" : { \"properties\" : { \"disclaimer\" : { \"type\" : \"string\" }, \"disclaimerTemplate\" : { \"type\" : \"string\" }, \"linkText\" : { \"type\" : \"string\" }, \"linkUrl\" : { \"type\" : \"string\" }, \"offerEndDate\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Offer End Date is required!\" }, { \"condition\" : \"dateFormat\", \"format\" : \"yyyy-MM-dd'T'HH:mm:ssXXX\", \"failureMessage\" : \"Offer End Date is not in the format 1900-01-01T10:10:10-05:00!\" }, { \"condition\" : \"greaterThan\", \"compareWith\" : \"content.offerStartDate\", \"failureMessage\" : \"Offer End Date should be greater than Offer Start Date!\" } ] }, \"offerStartDate\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Offer Start Date is required!\" }, { \"condition\" : \"dateFormat\", \"format\" : \"yyyy-MM-dd'T'HH:mm:ssXXX\", \"failureMessage\" : \"Offer Start Date is not in the format 1900-01-01T10:10:10-05:00!\" }, { \"condition\" : \"lessThan\", \"compareWith\" : \"content.offerEndDate\", \"failureMessage\" : \"Offer Start Date should be less than Offer End Date!\" } ] }, \"offers\" : { \"items\" : { \"properties\" : { \"bodyCopy\" : { \"type\" : \"string\" }, \"headLine\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Headline is required!\" } ] } }, \"type\" : \"object\" }, \"type\" : \"array\" }, \"promoCode\" : { \"type\" : \"string\" }, \"redeemingChannel\" : { \"type\" : \"string\", \"validations\" : { \"condition\" : \"enum\", \"enumeration\" : [ \"all\", \"online\", \"instore\" ], \"failureMessage\" : \"Redeeming Channels should be one of [all, online, instore] values!\" } } }, \"type\" : \"object\" }, \"contentType\" : { \"type\" : \"string\" }, \"displayName\" : { \"type\" : \"string\" }, \"name\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Name is required!\" } ] }, \"parent\" : { \"type\" : \"string\" }, \"priority\" : { \"type\" : \"integer\" }, \"publishingChannelApp\" : { \"type\" : \"boolean\" }, \"publishingChannelLarge\" : { \"type\" : \"boolean\" }, \"publishingChannelSmall\" : { \"type\" : \"boolean\" }, \"publishingEndDate\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Publishing End Date is required!\" }, { \"condition\" : \"greaterThan\", \"compareWith\" : \"content.offerStartDate\", \"failureMessage\" : \"Publishing End Date should be greater than Offer Start Date!\" }, { \"condition\" : \"greaterThan\", \"compareWith\" : \"publishingStartDate\", \"failureMessage\" : \"Publishing End Date should be greater than Publishing Start Date!\" } ] }, \"publishingStartDate\" : { \"type\" : \"string\", \"validations\" : [ { \"condition\" : \"notnull\", \"failureMessage\" : \"Publishing Start Date is required!\" }, { \"condition\" : \"lessThan\", \"compareWith\" : \"content.offerEndDate\", \"failureMessage\" : \"Publishing Start Date should be less than Offer End Date!\" } ] }, \"validated\" : { \"type\" : \"boolean\" } }, \"type\" : \"object\", \"required\" : [ \"validated\" ] } ";

    public static void main(String[] args) {
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
        List<String> pathList = using(conf).parse(json).read("$..validations");
        List dataList = JsonPath.read(json, "$..validations");
        for (String path : pathList) {
            System.out.println(path);
        }
        for (Object path : dataList) {
            System.out.println(path);
        }
    }
}
