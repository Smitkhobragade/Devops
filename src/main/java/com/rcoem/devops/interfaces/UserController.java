package com.rcoem.devops.interfaces;

import com.rcoem.devops.User;
import com.rcoem.devops.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    
    @PostMapping()
    ResponseEntity<String> createUser(@RequestBody User user){
        if(userService.existsByNameAndDept(user.getName(),user.getDepartment())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the same name and department already exists.");
        }
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if no users
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if user not found
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        boolean userExists = userService.updateUser(id, updatedUser);
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."); // 404 if user doesn't exist
        }
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        boolean userDeleted = userService.deleteUser(id);
        if (!userDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."); // 404 if user doesn't exist
        }
        return ResponseEntity.ok("User deleted successfully.");
    }
}
