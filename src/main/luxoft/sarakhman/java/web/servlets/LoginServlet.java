package web.servlets;

import services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Boolean isRealUser = securityService.checkPassword(name, password);
            if(isRealUser){
                resp.addCookie(securityService.createToken("user-token"));
                resp.sendRedirect("/products");
            }
                else{
                resp.sendRedirect("/login");
                //todo : добавить вывод о неправильном логине или пароле

            }

    }
}
