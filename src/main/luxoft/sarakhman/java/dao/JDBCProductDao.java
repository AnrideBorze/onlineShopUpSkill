package dao;

import entity.Product;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class JDBCProductDao implements ProductDao {

    private final DataSource dataSource;


    private static final String ADD_SQL = "INSERT INTO products (name, price, creation_date) VALUES (?, ?, ?);";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creation_date FROM products;";
    private static final String FIND_BY_ID_SQL = "SELECT id, name, price, creation_date FROM products WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products where id = ?";

    public JDBCProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, product.getCreationDate());
            preparedStatement.executeQuery();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Failed adding product to db", e);
        }
    }

    public Product updateProduct(Product product) {
        deleteProduct(product.getId());
        addProduct(product);
        return product;
    }

    public Boolean deleteProduct(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Deleting product was failed", e);
        }
    }

    public Product getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            Product product = ProductRowMapper.mapRow(resultSet);

            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find the product with this id", e);
        }
    }
}
