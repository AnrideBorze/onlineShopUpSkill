package services;

import dao.UserDao;
import entity.User;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

public class SecurityService {

    private final UserDao userDao;
    private List<String> tokens;

    public SecurityService(UserDao userDao, List<String> tokens) {
        this.tokens = tokens;
        this.userDao = userDao;
    }

    public boolean checkPassword(String name, String password) {
        User user = userDao.findUserByName(name);
        return password.equals(user.getPassword());
    }

    public Cookie createToken(String tokenName) {
        String token = UUID.randomUUID().toString();

        Cookie cookie = new Cookie(tokenName, token);
        return cookie;

    }


}
