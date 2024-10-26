package com.rcoem.devops.services;

import com.rcoem.devops.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    HashMap<String, User> users;

    @PostConstruct
    public void setup(){
        users = new HashMap<>();
    }

    public String createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        users.put(user.getUserId(),user);
        return user.getUserId();
        // Add Validation to it.so that same name and Dept. person's ID doesn't get created again.
        //  Try one at Controller Level and One at Service Level
    }

    public List<User> getAllUsers(){
        return users.values().stream().collect(Collectors.toList());
    }

    public User getUser(String id){
        return users.getOrDefault(id,null);
    }

    public boolean updateUser(String id, User updatedUser) {
        if (!users.containsKey(id)) {
            return false; // User not found
        }
        updatedUser.setUserId(id);
        users.put(id, updatedUser);
        return true;
    }

    public boolean deleteUser(String id) {
        if (!users.containsKey(id)) {
            return false; // User not found
        }
        users.remove(id);
        return true;
    }

    public boolean existsByNameAndDept(String name, String department) {
        return users.values().stream()
                .anyMatch(user -> user.getName().equalsIgnoreCase(name) && user.getDepartment().equalsIgnoreCase(department));
    }
}
