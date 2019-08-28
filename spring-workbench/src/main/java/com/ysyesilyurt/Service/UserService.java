package com.ysyesilyurt.Service;

import com.ysyesilyurt.Dao.IUserDao;
import com.ysyesilyurt.EntityModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("fake-data") /* Using DAO implementation of fake data */
    private IUserDao UserDao;

    /*
    *  If I were not to make Dao a Repository (a Component/Bean) Then I would need sth like following:
    *   private UserDao userDao = new UserDao();
    *
    *   Which would be pretty LAME, since in this way I would end up 'new'ing everything and a highly coupled
    *   SOA.
    * */

    public Collection<User> getAllUsers() {
        return UserDao.getAllUsers();
    }

    public User getUserById(int id) {
        return UserDao.getUserById(id);
    }

    public boolean updateUserById(int id, User updatedCredentials) {
        return UserDao.updateUserById(id, updatedCredentials);
    }

    public int createUser(User userCredentials) {
        return UserDao.createUser(userCredentials);
    }

    public boolean deleteUserById(int id) {
        return UserDao.deleteUserById(id);
    }
}
