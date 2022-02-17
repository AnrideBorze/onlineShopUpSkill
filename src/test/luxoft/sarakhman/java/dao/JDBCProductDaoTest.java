package dao;

import entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class JDBCProductDaoTest {
    JDBCProductDao jdbcProductDao;

    @Test
    public void getAllProductsWorkCorrectly() {

    }

    @Test
    public void addProductChangeSizeOfDB() {

    }

    @Test
    public void updateProductWorkCorrectly() {

    }

    @Test
    public void deleteProductWorkCorrectly() {

    }

    private List<Product> creatingTwoProducts() {
        List<Product> preparedProducts = new ArrayList<>();
        Product phone = new Product();
        phone.setPrice(499.99);
        phone.setCreationDate(Timestamp.valueOf("2018-09-01 09:00:00"));
        phone.setId(1);
        phone.setName("phone");
        Product television = new Product();
        television.setPrice(999.99);
        television.setCreationDate(Timestamp.valueOf("2018-09-01 10:00:00"));
        television.setId(2);
        television.setName("TV");
        preparedProducts.add(phone);
        preparedProducts.add(television);
        return preparedProducts;
    }

    private Product createProduct() {
        Product car = new Product();
        car.setPrice(1499.99);
        car.setCreationDate(Timestamp.valueOf("2018-09-01 11:00:00"));
        car.setId(3);
        car.setName("car");
        return car;
    }
}
