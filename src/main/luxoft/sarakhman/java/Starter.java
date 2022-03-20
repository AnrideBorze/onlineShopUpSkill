import config.PropertiesHolder;
import dao.JDBCProductDao;
import dao.JDBCUserDao;
import org.eclipse.jetty.servlet.FilterHolder;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import services.ProductService;
import services.SecurityService;
import web.filters.SecurityFilter;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.servlets.*;

import javax.servlet.DispatcherType;
import java.util.*;


public class Starter {
    public static void main(String[] args) throws Exception {
        PropertiesHolder propertiesHolder = new PropertiesHolder("application.properties");
        Properties properties = propertiesHolder.getProperties();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(properties.getProperty("jdbc_name"));
        dataSource.setUser(properties.getProperty("jdbc_user"));
        dataSource.setPassword(properties.getProperty("jdbc_password"));


        JDBCProductDao jdbcProductDao = new JDBCProductDao(dataSource);
        JDBCUserDao jdbcUserDao = new JDBCUserDao(dataSource);

        Flyway flyway = Flyway.configure().dataSource(properties.getProperty("jdbc_url"),
                properties.getProperty("jdbc_user"),
                properties.getProperty("jdbc_password")).load();
        flyway.migrate();

        List<String> tokens = Collections.synchronizedList(new ArrayList<>());

        ProductService productService = new ProductService(jdbcProductDao);
        SecurityService securityService = new SecurityService(jdbcUserDao, tokens);


        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        SecurityFilter securityFilter = new SecurityFilter(securityService);

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


        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}
