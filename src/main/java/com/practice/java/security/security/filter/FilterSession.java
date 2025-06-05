package com.practice.java.security.security.filter;

import com.practice.java.security.entities.User;
import com.practice.java.security.service.auth.JwtService;
import com.practice.java.security.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterSession extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.split(" ")[1];

        String username = jwtService.claimSubject(token, Claims::getSubject);

        User userDb = userService.findByUsername(username);

        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                username,null, userDb.getAuthorities()
        );

        userAuthentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);

        filterChain.doFilter(request,response);
    }
}
