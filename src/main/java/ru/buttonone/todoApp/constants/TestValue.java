package ru.buttonone.todoApp.constants;

import java.math.BigInteger;

import static ru.buttonone.todoApp.utils.Props.getProperty;

public class TestValue {
    public static final String APP_PROP = "application.properties";
    private static final String CREDENTIALS_PROP = "credentials.properties";
    public static final BigInteger FIRST_TASK_ID = new BigInteger(getProperty(APP_PROP, "first-task-id"));
    public static final BigInteger SECOND_TASK_ID = new BigInteger(getProperty(APP_PROP, "second-task-id"));
    public static final BigInteger THIRD_TASK_ID = new BigInteger(getProperty(APP_PROP, "third-task-id"));
    public static final BigInteger FOURTH_TASK_ID = new BigInteger(getProperty(APP_PROP, "fourth-task-id"));
    public static final BigInteger FIFTH_TASK_ID = new BigInteger(getProperty(APP_PROP, "fifth-task-id"));
    public static final BigInteger SIXTH_TASK_ID = new BigInteger(getProperty(APP_PROP, "sixth-task-id"));
    public final static String FIRST_TASK_TEXT = getProperty(APP_PROP, "first-task-text");
    public final static String SECOND_TASK_TEXT = getProperty(APP_PROP, "second-task-text");
    public final static String THIRD_TASK_TEXT = getProperty(APP_PROP, "third-task-text");
    public final static String FOURTH_TASK_TEXT = getProperty(APP_PROP, "fourth-task-text");
    public final static String FIFTH_TASK_TEXT = getProperty(APP_PROP, "fifth-task-text");
    public final static String SIXTH_TASK_TEXT = getProperty(APP_PROP, "sixth-task-text");
    public final static String USERNAME = getProperty(CREDENTIALS_PROP, "username");
    public final static String PASSWORD = getProperty(CREDENTIALS_PROP, "password");
    public final static BigInteger FILTER_MIN_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "filter-minimum-allow-value"));
    public final static BigInteger FILTER_MAX_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "filter-maximum-allow-value"));
    public final static BigInteger FILTER_LESS_MIN_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "filter-less-minimum-allow-value"));
    public final static BigInteger FILTER_MORE_MAX_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "filter-more-maximum-allow-value"));
    public final static BigInteger FILTER_MIDDLE_RANGE_VALUE = new BigInteger(getProperty(APP_PROP, "filter-middle-range-value"));
    public static final String TASK_SCHEMA_PATH = "src/test/resources/task-schema.json";
    public final static BigInteger ID_MIN_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "id-min-allow-value"));
    public final static BigInteger ID_MAX_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "id-max-allow-value"));
    public final static BigInteger ID_LESS_MIN_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "id-less-min-allow-value"));
    public final static BigInteger ID_MORE_MAX_ALLOW_VALUE = new BigInteger(getProperty(APP_PROP, "id-more-max-allow-value"));
}