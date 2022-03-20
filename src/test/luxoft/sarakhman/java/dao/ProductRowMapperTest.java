//package dao;
//
//import entity.Product;
//import org.junit.jupiter.api.Test;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//
//public class ProductRowMapperTest {
//
//    @Test
//    public void rowMapWorkCorrectly() throws SQLException {
//        Product expectedProduct = createProduct();
//
//        ResultSet resultSet = mock(ResultSet.class);
//
//        when(resultSet.getString("name")).thenReturn("car");
//        when(resultSet.getDouble("price")).thenReturn(1499.99);
//        when(resultSet.getInt("id")).thenReturn(3);
//        when(resultSet.getTimestamp("creation_date")).thenReturn(Timestamp.valueOf("2018-09-01 11:00:00"));
//
//
//        Product actualProduct = ProductRowMapper.mapRow(resultSet);
//
//        assertEquals(expectedProduct, actualProduct);
//        assertNotNull(actualProduct);
//    }
//
//
//    private Product createProduct() {
//        Product car = new Product();
//        car.setPrice(1499.99);
//        car.setCreationDate(Timestamp.valueOf("2018-09-01 11:00:00"));
//        car.setId(3);
//        car.setName("car");
//        return car;
//    }
//
//
//}
//
//
//
