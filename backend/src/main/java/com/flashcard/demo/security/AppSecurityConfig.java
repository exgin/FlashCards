package com.flashcard.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // antMatchers() white list files that anyone can see
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*,/js/*,/jsx/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails billy = User.builder()
                .username("bill") // username
                .password(passwordEncoder.encode("billy1")) // password
                .roles(AppUserRole.NORMAL.name()) // normal_role
                .build();

        UserDetails sam = User.builder()
                .username("sam")
                .password(passwordEncoder.encode("sam1"))
                .roles(AppUserRole.ADMIN.name()) // admin
                .build();

        return new InMemoryUserDetailsManager(billy, sam);
    }

}
