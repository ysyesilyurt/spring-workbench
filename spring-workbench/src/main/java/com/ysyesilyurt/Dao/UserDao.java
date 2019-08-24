package com.ysyesilyurt.Dao;

import com.ysyesilyurt.EntityModel.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

@Repository
public class UserDao {

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

    public Collection<User> getAllUsers() {
        return this.users.values();
    }

}
