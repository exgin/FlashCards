package com.flashcard.demo.security;

public enum AppUserPermission {
    CARD_READ("card:read"),
    CARD_WRITE("card:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
