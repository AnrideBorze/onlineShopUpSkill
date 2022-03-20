package config;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHolder {

    private final Properties properties = new Properties();

    public PropertiesHolder(String path) {
        loadProperties(path);
    }

    public Properties loadProperties(String path) {
        try(InputStream resource = PropertiesHolder.class.getClassLoader().getResourceAsStream(path)) {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}
