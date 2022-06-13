package book.pojo.service.Impl;

import book.pojo.User;
import book.pojo.dao.UserDAO;
import book.pojo.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }
}
