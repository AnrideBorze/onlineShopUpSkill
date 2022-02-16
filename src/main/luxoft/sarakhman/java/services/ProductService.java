package services;

import dao.JDBCProductDao;
import entity.Product;

import java.util.List;

public class ProductService {
    private JDBCProductDao productDao;

    public ProductService(JDBCProductDao JDBCProductDao) {
        this.productDao = JDBCProductDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Product updateProduct(Product product){
        return productDao.updateProduct(product);
    }

    public Product deleteProduct(long id) {
        return productDao.deleteProduct(id);    }
}
