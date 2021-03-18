package com.flashcard.demo.security;

import com.flashcard.demo.jwt.JwtTokenVerifier;
import com.flashcard.demo.jwt.JwtUsernameAndPasswordAuthFilter;
import com.flashcard.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.flashcard.demo.security.AppUserPermission.*;
import static com.flashcard.demo.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // antMatchers() white list files that anyone can see
        // add authority to creating flashcards

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(CARD_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(CARD_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(CARD_WRITE.getPermission())
                .antMatchers("/api/flashcards/").hasAnyRole(NORMAL.name(), ADMIN.name())
                .antMatchers("/","index","/css/*,/js/*,/jsx/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }

}
