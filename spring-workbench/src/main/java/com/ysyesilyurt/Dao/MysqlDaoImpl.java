package com.ysyesilyurt.Dao;

import com.ysyesilyurt.EntityModel.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("mysql-data")
public class MysqlDaoImpl implements IUserDao {

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean updateUserById(int id, User updatedCredentials) {
        return false;
    }

    @Override
    public int createUser(User userCredentials) {
        return 0;
    }

    @Override
    public boolean deleteUserById(int id) {
        return false;
    }
}
