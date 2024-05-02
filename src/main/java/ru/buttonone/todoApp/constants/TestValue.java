package ru.buttonone.todoApp.constants;

import static java.lang.Long.parseLong;
import static ru.buttonone.todoApp.utils.Props.getProperty;

public class TestValue {
    public static final String APP_PROP = "application.properties";
    private static final String CREDENTIALS_PROP = "credentials.properties";
    public static final long TASK_ID = parseLong(getProperty(APP_PROP, "task-id"));
    public final static String TASK_TEXT = getProperty(APP_PROP, "task-text");
    public final static String USERNAME = getProperty(CREDENTIALS_PROP, "username");
    public final static String PASSWORD = getProperty(CREDENTIALS_PROP, "password");
}