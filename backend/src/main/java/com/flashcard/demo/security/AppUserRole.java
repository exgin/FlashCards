package com.flashcard.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AppUserRole {
    NORMAL(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(AppUserPermission.NORMAL_READ, AppUserPermission.NORMAL_WRITE,
            AppUserPermission.CARD_READ, AppUserPermission.CARD_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }
}
