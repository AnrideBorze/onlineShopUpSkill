package services;

import dao.ProductDao;
import entity.Product;

import java.util.List;


public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public Product deleteProduct(long id) {
        return productDao.deleteProduct(id);
    }
}
