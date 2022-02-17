package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHolder {

    private static final String DB_URL_PROPERTY_NAME = "spring.datasource.url";
    private static final String DB_NAME_PROPERTY_NAME = "spring.datasource.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "spring.datasource.password=qwerty007";

    private final Properties properties = new Properties();

    public String getUrl() {
        return DB_URL_PROPERTY_NAME;
    }

    public String getName() {
        return DB_URL_PROPERTY_NAME;
    }

    public String getPassword() {
        return DB_URL_PROPERTY_NAME;
    }

    public PropertiesHolder(String path) {
        loadProperties(path);
    }

    public Properties loadProperties(String path) {
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}
