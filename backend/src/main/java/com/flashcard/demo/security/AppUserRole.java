package com.flashcard.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
    NORMAL(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            AppUserPermission.CARD_READ, AppUserPermission.CARD_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> perms = getPermissions()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
                .collect(Collectors.toSet());

        perms.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return perms;
    }
}
