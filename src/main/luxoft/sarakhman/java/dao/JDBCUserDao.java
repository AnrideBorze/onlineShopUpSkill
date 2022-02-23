package dao;

import entity.Product;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {
    private ConnectionFactory connectionFactory;

    private static final String FIND_BY_NAME_SQL = "SELECT id, name, password  FROM users WHERE name = ?";


    @Override
    public User findUserByName(String name) {
        try (Connection connection = connectionFactory.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            User user = UserRowMapper.mapRow(resultSet);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find the product with this id", e);
        }
    }
}
