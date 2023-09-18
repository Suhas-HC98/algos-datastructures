package com.shared.algo.controller;

import com.shared.algo.model.JwtRequest;
import com.shared.algo.model.JwtResponse;
import com.shared.algo.service.impl.UserService;
import com.shared.algo.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public final class AuthenticationController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService service;

    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.username(), jwtRequest.password()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", badCredentialsException);
        }
        UserDetails userDetail = service.loadUserByUsername(jwtRequest.username());
        String token = jwtUtils.generateToken(userDetail);
        return JwtResponse.builder().jwtToken(token).build();
    }
}
