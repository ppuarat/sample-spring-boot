package com.example.base.controller;

import com.example.base.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("users")
public class UserController {
    private List<User> userList = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public UserController() {
        userList.add(new User(counter.incrementAndGet(), "John Doe"));
        userList.add(new User(counter.incrementAndGet(), "Carla Doe"));
    }

    @GetMapping
    public List<User> getUsers() {
        return userList;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userList.add(user);
        return user;
    }
}
