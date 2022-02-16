package web.servlets;

import entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductServletTest {
    ProductServlet productServlet;

    @Test
    public void getAllProductsCallCorrectMethodInProductService() {
        ProductService mockService = mock(ProductService.class);
        productServlet = new ProductServlet(mockService);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        productServlet.doGet(request,response);
        verify(productServlet.findAll());
    }

    @Test
    public void findAllProductsReturnEmptyListOnEmptyDB() {
        List product = productServlet.findAll();
        assertEquals(0,product.size());
        assertTrue(product.isEmpty());

    }

    @Test
    public void getAllProductsReturnNotEmptyListOnNotEmptyDB() {
        List<Product> preparedProducts = creatingTwoProducts();
        ProductService mockService = mock(ProductService.class);
        productServlet = new ProductServlet(mockService);
        when(mockService.findAll()).thenReturn(preparedProducts);


        List<Product> product = productServlet.findAll();

        assertFalse(product.isEmpty());
        Product one = product.get(0);
        assertEquals(one.getId(),1);
        assertEquals(one.getName(),"phone");
        assertEquals(one.getPrice(),499.99);
        assertEquals(one.getCreationDate(),(Timestamp.valueOf("2018-09-01 09:00:00")));
        Product second = product.get(1);
        assertEquals(second.getId(),2);
        assertEquals(second.getName(),"TV");
        assertEquals(second.getPrice(),999.99);
        assertEquals(second.getCreationDate(),(Timestamp.valueOf("2018-09-01 10:00:00")));
    }






    private List<Product> creatingTwoProducts(){
        List<Product> preparedProducts = new ArrayList<>();
        Product one = new Product();
        one.setPrice(499.99);
        one.setCreationDate(Timestamp.valueOf("2018-09-01 09:00:00"));
        one.setId(1);
        one.setName("phone");
        Product second = new Product();
        second.setPrice(999.99);
        second.setCreationDate(Timestamp.valueOf("2018-09-01 10:00:00"));
        second.setId(2);
        second.setName("TV");
        preparedProducts.add(one);
        preparedProducts.add(second);
        return preparedProducts;
    }
}
