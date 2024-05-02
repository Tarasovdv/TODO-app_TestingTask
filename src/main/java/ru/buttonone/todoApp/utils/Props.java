package ru.buttonone.todoApp.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Props {
    private final static Properties PROPERTIES = new Properties();

    private static Properties readProperty(String nameProperty) {
        try {
            PROPERTIES.load(Props.class
                    .getClassLoader()
                    .getResourceAsStream(nameProperty));
        } catch (IOException e) {
            log.error("Error load config file " + nameProperty);
            throw new RuntimeException(e);
        }
        return PROPERTIES;
    }

    public static String getProperty(String nameProperty, String key) {
        return readProperty(nameProperty).getProperty(key);
    }
}