package com.flashcard.demo.user;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserByName(String username);
    List<User> getAllUsers();
}
