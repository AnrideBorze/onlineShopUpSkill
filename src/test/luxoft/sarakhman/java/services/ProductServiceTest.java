//package services;
//
//import dao.JDBCProductDao;
//import entity.Product;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class ProductServiceTest {
//    ProductService productService;
//
//
//    @Test
//    public void getAllProductsCallCorrectMethodInProductDao() {
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//
//
//        productService.findAll();
//        verify(mockDao).findAll();
//    }
//
//    @Test
//    public void addProductCallCorrectMethodInProductDao() {
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//        Product product = new Product();
//
//        productService.addProduct(product);
//        verify(mockDao).addProduct(product);
//    }
//
//    @Test
//    public void addProductChangeSizeOgDB() {
//        List<Product> prepareProducts = creatingTwoProducts();
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//        Product product = createProduct();
//
//        when(mockDao.findAll()).thenReturn(prepareProducts);
//
//        List<Product> listBeforeIncrement = productService.findAll();
//        assertEquals(2, listBeforeIncrement.size());
//
//        productService.addProduct(product);
//
//        List<Product> listAfterIncrement = productService.findAll();
//
//        assertEquals(1499.99, listAfterIncrement.get(2).getPrice());
//        assertEquals("2018-09-01 11:00:00", listAfterIncrement.get(2).getCreationDate());
//        assertEquals(3, listAfterIncrement.get(2).getId());
//        assertEquals("car", listAfterIncrement.get(2).getName());
//        assertNotEquals(listBeforeIncrement.size(), listAfterIncrement.size());
//        assertEquals(3, listAfterIncrement.size());
//
//
//    }
//
//
//    @Test
//    public void updateProductCallCorrectMethodInProductDao() {
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//        Product product = new Product();
//
//        productService.updateProduct(product);
//        verify(mockDao).updateProduct(product);
//
//    }
//
//    @Test
//    public void updateProductWorkCorrectly() {
//        List<Product> prepareProducts = creatingTwoProducts();
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//        Product product = createProduct();
//
//        when(mockDao.findAll()).thenReturn(prepareProducts);
//
//        product.setId(2);
//
//        productService.updateProduct(product);
//
//        List<Product> listAfterUpdate = productService.findAll();
//        assertEquals(1499.99, listAfterUpdate.get(1).getPrice());
//        assertEquals("2018-09-01 11:00:00", listAfterUpdate.get(1).getCreationDate());
//        assertEquals(2, listAfterUpdate.get(1).getId());
//        assertEquals("car", listAfterUpdate.get(1).getName());
//    }
//
//    @Test
//    public void deleteProductCallCorrectMethodInProductDao() {
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//        Product product = new Product();
//
//        productService.updateProduct(product);
//        verify(mockDao).updateProduct(product);
//
//    }
//
//    @Test
//    public void deleteProductChangeSizeOfDB() {
//        List<Product> prepareProducts = creatingTwoProducts();
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//
//        when(mockDao.findAll()).thenReturn(prepareProducts);
//
//        List<Product> productsBeforeDelete = productService.findAll();
//        assertEquals(2, productsBeforeDelete.size());
//        assertFalse(productsBeforeDelete.isEmpty());
//
//        productService.deleteProduct(1);
//        List<Product> currentProducts = productService.findAll();
//        assertEquals(1, currentProducts.size());
//        assertFalse(currentProducts.isEmpty());
//
//        productService.deleteProduct(0);
//        currentProducts = productService.findAll();
//        assertEquals(0, currentProducts.size());
//        assertTrue(currentProducts.isEmpty());
//    }
//
//    @Test
//    public void deleteProductDoNotChangeSizeOfEmptyDB() {
//        List<Product> prepareProducts = new ArrayList<>();
//        JDBCProductDao mockDao = mock(JDBCProductDao.class);
//        productService = new ProductService(mockDao);
//
//        when(mockDao.findAll()).thenReturn(prepareProducts);
//
//        List<Product> productsBeforeDelete = productService.findAll();
//        assertEquals(0, productsBeforeDelete.size());
//        assertTrue(productsBeforeDelete.isEmpty());
//
//        productService.deleteProduct(1);
//        List<Product> currentProducts = productService.findAll();
//        assertEquals(0, currentProducts.size());
//        assertTrue(currentProducts.isEmpty());
//
//
//    }
//
//
//    private List<Product> creatingTwoProducts() {
//        List<Product> preparedProducts = new ArrayList<>();
//        Product phone = new Product();
//        phone.setPrice(499.99);
//        phone.setCreationDate(Timestamp.valueOf("2018-09-01 09:00:00"));
//        phone.setId(1);
//        phone.setName("phone");
//        Product television = new Product();
//        television.setPrice(999.99);
//        television.setCreationDate(Timestamp.valueOf("2018-09-01 10:00:00"));
//        television.setId(2);
//        television.setName("TV");
//        preparedProducts.add(phone);
//        preparedProducts.add(television);
//        return preparedProducts;
//    }
//
//    private Product createProduct() {
//        Product car = new Product();
//        car.setPrice(1499.99);
//        car.setCreationDate(Timestamp.valueOf("2018-09-01 11:00:00"));
//        car.setId(3);
//        car.setName("car");
//        return car;
//    }
//}
