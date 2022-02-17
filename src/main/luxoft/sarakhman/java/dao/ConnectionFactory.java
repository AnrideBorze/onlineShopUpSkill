package dao;

import config.PropertiesHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final PropertiesHolder propertiesHolder;


    public ConnectionFactory(PropertiesHolder propertiesHolder) {
        this.propertiesHolder = propertiesHolder;
    }

    protected Connection getConnect() {
        try {
            Properties properties = propertiesHolder.getProperties();
            return DriverManager.getConnection(properties.getProperty(propertiesHolder.getUrl()),
                    properties.getProperty(propertiesHolder.getName()),
                    properties.getProperty(propertiesHolder.getPassword()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
