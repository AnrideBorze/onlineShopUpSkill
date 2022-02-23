package dao;

import entity.User;

public interface UserDao {

    public User findUserByName(String name);

}
