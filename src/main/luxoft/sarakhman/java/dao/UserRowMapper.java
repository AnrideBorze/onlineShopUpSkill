package dao;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRowMapper {
    public static User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        return user;

    }
}
