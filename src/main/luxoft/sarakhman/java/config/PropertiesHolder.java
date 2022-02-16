package config;

public class PropertiesHolder {

    private static final String DB_URL_PROPERTY_NAME = "spring.datasource.url";
    private static final String DB_NAME_PROPERTY_NAME = "spring.datasource.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "spring.datasource.password=qwerty007";

    public String getUrl() {
        return DB_URL_PROPERTY_NAME;
    }

    public String getName() {
        return DB_URL_PROPERTY_NAME;
    }

    public String getPassword() {
        return DB_URL_PROPERTY_NAME;
    }

}
