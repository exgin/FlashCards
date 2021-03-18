package com.flashcard.demo.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// every request, test if the token is valid
public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = req.getHeader("Authorization");

        // reject request if invalid
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(req, res);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");

        try {
            String secretKey = "securesecuresecuresecuresecuresecure";

            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            var auths = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = auths.stream()
                    .map(el -> new SimpleGrantedAuthority(el.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication); // sets true if there is a valid token

        } catch (Exception e) {
            throw new IllegalStateException(String.format("Token %s can't be trusted", token));
        }

        filterChain.doFilter(req, res); // pass onto the next
    }
}
