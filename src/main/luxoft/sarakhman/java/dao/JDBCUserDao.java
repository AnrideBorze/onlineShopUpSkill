package dao;

import entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUserDao implements UserDao {
    private final DataSource dataSource;

    private static final String FIND_BY_NAME_SQL = "SELECT id, name, password  FROM users WHERE name = ?";

    public JDBCUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User findUserByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            User user = UserRowMapper.mapRow(resultSet);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find the product with this id", e);
        }
    }
}
