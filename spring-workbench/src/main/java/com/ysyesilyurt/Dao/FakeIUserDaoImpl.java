package com.ysyesilyurt.Dao;

import com.ysyesilyurt.EntityModel.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fake-data")
// @AllArgsConstructor -> dont need this since we dont have a member that needs to be initialized
public class FakeIUserDaoImpl implements IUserDao {

    private static Map<Integer, User> users;

    // Fake Database for the Workbench App
    static {
        // DOUBLE-BRACE-INITIALIZATION
        users = new HashMap<Integer, User>() {
            {
                put(1, new User(1, "yavuz", "student", 21));
                put(2, new User(2, "django", "boss", 11));
                put(3, new User(3, "jackson", "employee", 31));
                put(4, new User(4, "spring", "second boss", 41));
                put(5, new User(5, "dropwizard", "janitor", 51));
            }
        };

        // COULD HAVE USE Map.of, but in this way "users" would be immutable
        // this works for up to 10 elements:
//        users = Map.of(
//                1, new User(1, "yavuz", "student", 21),
//                2, new User(2, "django", "boss", 11)
//        );

        // OR COULD HAVE USER Map.ofEntries, immutable "users" also.
        // this works for any number of elements:
//        users = Map.ofEntries(
//                entry(1, new User(1, "yavuz", "student", 21)),
//                entry(2, new User(2, "django", "boss", 11)),
//                entry(3, new User(3, "jackson", "employee", 31)),
//                entry(4, new User(4, "spring", "second boss", 41)),
//                entry(5, new User(5, "dropwizard", "janitor", 51))
//        );
    }

    @Override
    public Collection<User> getAllUsers() {
        return this.users.values();
    }

    @Override
    public User getUserById(int id) {
        return this.users.get(id);
    }

    @Override
    public boolean updateUserById(int id, User updatedCredentials) {
        try {
            User user = this.users.get(id);
            user.setName(updatedCredentials.getName());
            user.setProfession(updatedCredentials.getProfession());
            user.setAge(updatedCredentials.getAge());
            return true;
        }
        catch (NullPointerException e) {
            System.out.println(String.format("Error in UserDAO: No such user with id %d", id));
            return false;
        }
    }

    @Override
    public int createUser(User userCredentials) {
        try {
            Integer id = userCredentials.getId();
            if (id == 0)
                id = getNextAvailableId();
            User user = new User(id, userCredentials.getName(), userCredentials.getProfession(), userCredentials.getAge());
            this.users.put(id, user);
            return id;
        }
        catch (Exception e) {
            System.out.println(String.format("Error in UserDAO: %s", e));
            return -1;
        }
    }

    @Override
    public boolean deleteUserById(int id) {
        try {
            users.remove(id);
            return true;
        }
        catch (NullPointerException e) {
            System.out.println(String.format("Error in UserDAO: No such user with id %d", id));
            return false;
        }
    }

    private int getNextAvailableId() {
        return Collections.max(this.users.keySet()) + 1;
    }

}
