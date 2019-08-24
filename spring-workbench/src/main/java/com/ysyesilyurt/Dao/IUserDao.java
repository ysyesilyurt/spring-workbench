package com.ysyesilyurt.Dao;

import com.ysyesilyurt.EntityModel.User;

import java.util.Collection;
import java.util.Collections;

public interface IUserDao {
    Collection<User> getAllUsers();

    User getUserById(int id);

    boolean updateUserById(int id, User updatedCredentials);

    int createUser(User userCredentials);

    boolean deleteUserById(int id);
}
