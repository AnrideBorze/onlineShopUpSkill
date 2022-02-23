package web.servlets;

import entity.Product;
import services.ProductService;
import web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServlet extends HttpServlet {

    private final ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = findAll();

        response.setStatus(HttpServletResponse.SC_OK);

        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productList);
        String page = pageGenerator.getPage("products.html", pageVariables);
        try {
            response.getWriter().println(page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<Product> findAll() {
        return productService.findAll();
    }
}
