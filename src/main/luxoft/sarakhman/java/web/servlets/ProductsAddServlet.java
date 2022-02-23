package web.servlets;

import entity.Product;
import services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class ProductsAddServlet extends HttpServlet {

    private final ProductService productService;

    public ProductsAddServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        Product product = creatingProduct(req);
        validations(product);
        productService.addProduct(product);
        resp.setStatus(200);

        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            throw new RuntimeException("Cannot redirect to all products", e);
        }

    }

    private Product creatingProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        Timestamp creationDate = Timestamp.valueOf(request.getParameter("creationDate"));
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCreationDate(creationDate);
        return product;
    }

    private void validations(Product product) {
        if (product.getName() == null || product.getName().length() > 0) {
            throw new RuntimeException("There is empty product name. Please write product name");
        } else if (product.getPrice() == 0) {
            throw new RuntimeException("There is no price for product. Please write product price");
        }
    }
}
