package com.flashcard.demo.user;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.flashcard.demo.security.AppUserRole.ADMIN;
import static com.flashcard.demo.security.AppUserRole.NORMAL;

@Repository("fake")
public class UserDAOImpl implements UserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /* refactor to a database */

    @Override
    public Optional<User> getUserByName(String username) {
        return getAllUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = Lists.newArrayList(
                new User(
                        "bill",
                        passwordEncoder.encode("billy1"),
                        NORMAL.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        "sam",
                        passwordEncoder.encode("sam1"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );


        return users;
    }

}
