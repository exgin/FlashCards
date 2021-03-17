package com.flashcard.demo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final List<User> users = Arrays.asList(new User(1, "bill2"));

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(userId + ", Does not exist."));
    }
}
