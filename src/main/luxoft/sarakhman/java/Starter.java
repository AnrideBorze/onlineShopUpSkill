import config.PropertiesHolder;
import dao.JDBCProductDao;
import dao.JDBCUserDao;
import org.eclipse.jetty.servlet.FilterHolder;
import services.ProductService;
import services.SecurityService;
import web.filters.SecurityFilter;
import web.servlets.*;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class Starter {
    public static void main(String[] args) throws Exception {
        PropertiesHolder propertiesHolder = new PropertiesHolder("application.properties");

        JDBCProductDao jdbcProductDao = new JDBCProductDao();
        JDBCUserDao jdbcUserDao = new JDBCUserDao();

        List<String> tokens = new ArrayList<>();

        ProductService productService = new ProductService(jdbcProductDao);
        SecurityService securityService = new SecurityService(jdbcUserDao, tokens);


        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        SecurityFilter securityFilter = new SecurityFilter();

        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productService);
        ProductServlet productServlet = new ProductServlet(productService);
        ProductUpdateServlet productUpdateServlet = new ProductUpdateServlet(productService);
        ProductDeleteServlet productDeleteServlet = new ProductDeleteServlet(productService);
        LoginServlet loginServlet = new LoginServlet(securityService);

        contextHandler.addServlet(new ServletHolder(productServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(productUpdateServlet), "/products/update");
        contextHandler.addServlet(new ServletHolder(productDeleteServlet), "/products/delete");
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");


        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/update", EnumSet.of(DispatcherType.FORWARD));
        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/add", EnumSet.of(DispatcherType.FORWARD));
        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/delete", EnumSet.of(DispatcherType.FORWARD));


        Server server = new Server(5432);
        server.setHandler(contextHandler);

        server.start();
    }
}
