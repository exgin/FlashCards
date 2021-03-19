package com.flashcard.demo.user.dao;

import com.flashcard.demo.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserByName(String username);
    List<User> getAllUsers();
}
