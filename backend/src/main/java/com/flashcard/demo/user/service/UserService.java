package com.flashcard.demo.user.service;

import com.flashcard.demo.user.dao.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO
                .getUserByName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException((String.format("Username %s not found", username))));
    }
}
