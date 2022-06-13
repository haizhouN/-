package book.pojo.dao.impl;

import book.pojo.User;
import book.pojo.dao.UserDAO;
import myssm.basedao.BaseDAO;

public class UserImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select* from t_user where uname like ? and pwd like ?", uname, pwd);
    }
}
