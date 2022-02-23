package dao;

import entity.Product;

import java.util.List;

public interface ProductDao {


    public List<Product> findAll();

    public Product addProduct(Product product);

    public Product updateProduct(Product product);

    public Boolean deleteProduct(long id);
    public Product getById(long id);
}
