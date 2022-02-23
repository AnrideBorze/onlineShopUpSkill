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
        Product oldProduct = productDao.getById(product.getId());
        Product newProduct = new Product();
        if (product.getName() != null && product.getName().length() > 0) {
            newProduct.setName(product.getName());
        } else {
            newProduct.setName(oldProduct.getName());
        }
        if (product.getPrice() > 0) {
            newProduct.setPrice(product.getPrice());
        } else {
            newProduct.setPrice(oldProduct.getPrice());
        }
        newProduct.setCreationDate(oldProduct.getCreationDate());
        newProduct.setId(product.getId());
        productDao.updateProduct(newProduct);
        return oldProduct;
    }

    public Boolean deleteProduct(long id) {
        return productDao.deleteProduct(id);
    }
}
