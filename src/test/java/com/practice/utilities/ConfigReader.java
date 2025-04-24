package com.practice.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    // Static block to load properties file
    static {
        FileInputStream fis = null;
        try {
            // Path to your config file
            String configFilePath = "src/test/resources/config.properties";
            fis = new FileInputStream(configFilePath);
            properties.load(fis);
            logger.info("Configuration file loaded successfully from: {}", configFilePath);
        } catch (IOException e) {
            logger.error("Failed to load configuration file from path: src/test/resources/config.properties", e);
        } finally {
            // Ensure the file input stream is closed properly
            if (fis != null) {
                try {
                    fis.close();
                    logger.debug("FileInputStream closed successfully.");
                } catch (IOException e) {
                    logger.error("Failed to close FileInputStream.", e);
                }
            }
        }
    }

    // Retrieves a property by key
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property with key '{}' not found. Returning null.", key);
        }
        return value;
    }

    // Retrieves a property by key with a default value
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if (value.equals(defaultValue)) {
            logger.warn("Property with key '{}' not found. Returning default value: '{}'.", key, defaultValue);
        }
        return value;
    }
}
