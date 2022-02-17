package dao;

import entity.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class JDBCProductDao implements ProductDao {

    private ConnectionFactory connectionFactory;


    private static final String ADD = "INSERT INTO products (name, price, creation_date) VALUES (?, ?, ?);";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creation_date FROM products;";


    public List<Product> findAll() {
        try (Connection connection = connectionFactory.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ProductRowMapper.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
