import config.PropertiesHolder;
import dao.JDBCProductDao;
import services.ProductService;
import web.servlets.servlets.ProductServlet;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.servlets.servlets.ProductUpdateServlet;
import web.servlets.servlets.ProductsAddServlet;


public class Starter {
    public static void main(String[] args) throws Exception {
        PropertiesHolder propertiesHolder = new PropertiesHolder("application.properties");

        JDBCProductDao jdbcProductDao = new JDBCProductDao();

        ProductService productService = new ProductService(jdbcProductDao);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productService);
        ProductServlet productServlet = new ProductServlet(productService);
        ProductUpdateServlet productUpdateServlet = new ProductUpdateServlet(productService);

        contextHandler.addServlet(new ServletHolder(productServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add ");
        contextHandler.addServlet(new ServletHolder(productUpdateServlet), "/products/update ");



        Server server = new Server(5432);
        server.setHandler(contextHandler);

        server.start();
    }
}
