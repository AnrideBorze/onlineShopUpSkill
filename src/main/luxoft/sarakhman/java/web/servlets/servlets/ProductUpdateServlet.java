package web.servlets.servlets;

import entity.Product;
import services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductUpdateServlet extends HttpServlet {
    private final ProductService productService;

    public ProductUpdateServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp)  {

        Product product = creatingProduct(request);
        Product oldProduct = productService.updateProduct(product);
        if(oldProduct!=null){
            resp.setStatus(200);
        }

    }


    private Product creatingProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setId(id);
        return product;
    }
}
