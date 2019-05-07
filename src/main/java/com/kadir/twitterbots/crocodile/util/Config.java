package com.kadir.twitterbots.crocodile.util;

import com.kadir.twitterbots.exceptions.PropertyNotLoadedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 18:56
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static Properties properties;

    private Config() {

    }

    static {
        properties = new Properties();
        File propertyFile = new File(Constants.PROPERTIES_FILE_NAME);
        try (InputStream input = new FileInputStream(propertyFile)) {
            properties.load(input);
            logger.info("All properties loaded from file: {}", Constants.PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            throw new PropertyNotLoadedException(Constants.PROPERTIES_FILE_NAME, e);
        }
    }

    public static String getPropertyAsString(String key) {
        return properties.getProperty(key);
    }

    public static int getPropertyAsInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static String getPropertyAsString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getPropertyAsInt(String key, String defaultValue) {
        return Integer.parseInt(properties.getProperty(key, defaultValue));
    }
}
