package com.practice.java.security.service.auth;

import com.practice.java.security.dto.TokenAuth;
import com.practice.java.security.dto.UserAuth;
import com.practice.java.security.dto.UserDto;
import com.practice.java.security.entities.User;
import com.practice.java.security.entities.other.Role;
import com.practice.java.security.exceptions.edit.UserError;
import com.practice.java.security.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService serviceUser;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUser(UserAuth userAuth){
        Authentication authenticate = new UsernamePasswordAuthenticationToken(
                userAuth.getUsername(), userAuth.getPassword()
        );

        Authentication authResult = authManager.authenticate(authenticate);

        User userDetails = (User) authResult.getPrincipal();

        String token = jwtService.generateToken(userDetails, generatedExtraClaims(userDetails));
        return token;
    }

    public TokenAuth registerUser(UserDto userDto){

        serviceUser.userExists(userDto.getUsername());

        Role roleUser = userDto.getRole() == null? Role.CUSTOMER: userDto.getRole();

        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(roleUser)
                .enabled(true)
                .build();


        String token = jwtService.generateToken(user, generatedExtraClaims(user));

        Optional.ofNullable(serviceUser.userSave(user))
                .ifPresentOrElse(e-> logger.info("user {} saved successfully", user.getName()),()-> {
                    try {
                        throw new UserError("user not saved", user.getName());
                    } catch (UserError e) {
                        throw new RuntimeException(e);
                    }
                });

        return TokenAuth.builder()
                .name(userDto.getName())
                .token(token)
                .build();
    }

    public boolean validateToken(String jwt){
        return jwtService.validate(jwt);
    }

    private Map<String, Object> generatedExtraClaims(User user) {
        return Map.of(
                "role",user.getRoles().name(),
                "authorities", user.getAuthorities(),
                "name", user.getName()
        );
    }

    public User loggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null  || !authentication.isAuthenticated()){
            return null;
        }

        String username = (String) authentication.getPrincipal();

        User userDetails = serviceUser.findByUsername(username);

        return userDetails;
    }
}
