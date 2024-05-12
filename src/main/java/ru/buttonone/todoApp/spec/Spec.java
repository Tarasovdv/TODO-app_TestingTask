package ru.buttonone.todoApp.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.buttonone.todoApp.utils.Props;

import static ru.buttonone.todoApp.constants.TestValue.APP_PROP;

public class Spec {
    public static final String BASE_URI = Props.getProperty(APP_PROP, "base-url");

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BASE_URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }
}