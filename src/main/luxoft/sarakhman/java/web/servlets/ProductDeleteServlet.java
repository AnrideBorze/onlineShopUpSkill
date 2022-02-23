package web.servlets;

import services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDeleteServlet extends HttpServlet {

    private final ProductService productService;

    public ProductDeleteServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            resp.setStatus(200);
        } else {
            resp.setStatus(404);
        }
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            throw new RuntimeException("Cannot redirect to all products", e);
        }
    }
}
