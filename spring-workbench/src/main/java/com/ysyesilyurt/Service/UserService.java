package com.ysyesilyurt.Service;

import com.ysyesilyurt.Dao.UserDao;
import com.ysyesilyurt.EntityModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    /*
    *   Now we can autowire the Dao with the Service since we created them as Beans and they are only needed to be
    *   injected to be used by other beans, no need for lame "new"s, we create a highly decoupled SOA here!
    *   Spring will find an instance of that bean and inject it in here.
    * */
    @Autowired  /* Field Injection */
    private UserDao userDao;

    /*
    *  If I were not to make Dao a Repository (a Component/Bean) Then I would need sth like following:
    *   private UserDao userDao = new UserDao();
    *
    *   Which would be pretty LAME, since in this way I would end up 'new'ing everything and a highly coupled
    *   SOA.
    * */

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public boolean updateUserById(int id, User updatedCredentials) {
        return userDao.updateUserById(id, updatedCredentials);
    }

    public int createUser(User userCredentials) {
        return userDao.createUser(userCredentials);
    }

    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }
}
