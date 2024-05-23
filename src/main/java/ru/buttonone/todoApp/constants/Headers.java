package ru.buttonone.todoApp.constants;

import io.restassured.http.Header;

import java.util.Base64;

import static ru.buttonone.todoApp.utils.Props.getProperty;

public abstract class Headers {
    private static final String CREDENTIALS_PROP = "credentials.properties";
    private static final String AUTHORIZATION_VALUE = "Basic " + Base64.getEncoder()
            .encodeToString((getProperty(CREDENTIALS_PROP, "username") + ":" +
                    getProperty(CREDENTIALS_PROP, "password"))
                    .getBytes());
    public static final Header BASIC_AUTHORIZATION = new Header("Authorization", AUTHORIZATION_VALUE);
}