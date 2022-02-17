package dao;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper {


    public static Product mapRow(ResultSet resultSet) throws SQLException {
        int id = 0;
        id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCreationDate(creationDate);
        return product;

    }

}