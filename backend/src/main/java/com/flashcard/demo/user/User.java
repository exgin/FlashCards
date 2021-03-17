package com.flashcard.demo.user;

public class User {
    private final Integer userId;
    private final String username;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
