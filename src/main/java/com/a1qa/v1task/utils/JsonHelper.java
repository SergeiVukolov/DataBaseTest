package com.a1qa.v1task.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.io.File;
import java.io.IOException;

public class JsonHelper {
    private static ISettingsFile environment = new JsonSettingsFile("config.json");

    public static String getValueFromJson(String key) {
        return environment.getValue(String.format("/%s", key)).toString();
    }

    public static <T> T getJsonData(String pathToFile, Class<T> clazz) {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        File fileData = new File(pathToFile);
        T javaObject;
        try {
            javaObject = objectMapper.readValue(fileData, clazz);
        } catch (IOException e) {
            throw new RuntimeException("File not found or another error", e);
        }
        return javaObject;
    }
}