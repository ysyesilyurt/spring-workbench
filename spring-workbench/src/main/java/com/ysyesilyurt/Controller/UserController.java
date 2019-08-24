package com.ysyesilyurt.Controller;

import com.ysyesilyurt.EntityModel.User;
import com.ysyesilyurt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        // @PathVariable is to obtain some placeholder from the URI
        // example call: localhost:8080/users/0
        return userService.getUserById(id);
    }

//    @GetMapping("/user")
//    public User getUserById(@RequestParam(value = "id") int id) {
          // @RequestParam is to obtain an parameter from the URI as well
          // example call: (Need to call endpoint with the GET parameters in this case) localhost:8080/users/user?id=0
//        return userService.getUserById(id);
//    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody User updatedCredentials) {
        // I could have used a DTO for updatingCredentials of a user
        if (userService.updateUserById(id, updatedCredentials))
            return "Successfully Updated User.";
        else
            return String.format("Update Failed for User with id %d, user does not exist.", id);
    }

    @PostMapping
    public String createUser(@RequestBody User userCredentials) {
        // I could have used a DTO when creating a user also
        int res = userService.createUser(userCredentials);
        if (res != -1)
            return String.format("Successfully created user with id %d.", res);
        else
            return "Create operation failed.";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        if (userService.deleteUserById(id))
            return String.format("Successfully Deleted User with id %d.",id);
        else
            return String.format("Failed to delete user with id %d, user does not exist.", id);
    }
}
