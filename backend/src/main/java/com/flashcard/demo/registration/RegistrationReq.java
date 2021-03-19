package com.flashcard.demo.registration;

public class RegistrationReq {

    private final String username;
    private final String password;

    public RegistrationReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "RegistrationReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
