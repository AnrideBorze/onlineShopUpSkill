import config.PropertiesHolder;
import dao.JDBCProductDao;
import services.ProductService;
import web.servlets.ProductServlet;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Starter {
    public static void main(String[] args) throws Exception {
        PropertiesHolder propertiesHolder = new PropertiesHolder("application.properties");

        JDBCProductDao jdbcProductDao = new JDBCProductDao();

        ProductService productService = new ProductService(jdbcProductDao);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products");

        Server server = new Server(5432);
        server.setHandler(contextHandler);

        server.start();
    }
}
