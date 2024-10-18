package com.ufrrj.smartrent.services;

import com.ufrrj.smartrent.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    public String authenticateUser(String username, String password) {
        var user = userService.loadUserByUsername(username);

        var passwordToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(passwordToken);

        return jwtUtils.generateToken(user);
    }
}
