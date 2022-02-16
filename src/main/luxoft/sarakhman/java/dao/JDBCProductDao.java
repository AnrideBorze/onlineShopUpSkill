package dao;

import config.PropertiesHolder;
import entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDao implements ProductDao {


    private static final String ADD = "INSERT INTO products (name, price, creation_date) VALUES (?, ?, ?);";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creation_date FROM products;";

    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private static final PropertiesHolder propertiesHolder = new PropertiesHolder();

    protected static Connection connect() {
        try {
            return DriverManager.getConnection(propertiesHolder.getUrl(),
                    propertiesHolder.getName(),
                    propertiesHolder.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>();
    }

    public Product addProduct(Product product) {
        return product;
    }

    public Product updateProduct(Product product) {
        return product;
    }

    public Product deleteProduct(long id) {
        return new Product();
    }

}
