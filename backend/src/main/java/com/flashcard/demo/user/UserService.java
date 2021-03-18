package com.flashcard.demo.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserService(@Qualifier("fake") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO
                .getUserByName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException((String.format("Username %s not found", username))));
    }
}
