package com.flashcard.demo.jwt;

public class UserAndPasswordReq {
    private String username;
    private String password;

    public UserAndPasswordReq() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
