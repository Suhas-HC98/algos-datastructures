package com.shared.info.controller;

import com.shared.info.dto.JwtRequest;
import com.shared.info.service.impl.UserService;
import com.shared.info.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private final JwtRequest jwtRequest = JwtRequest.builder().username("test").password("test").build();

    private final JWTUtils jwtUtils = mock(JWTUtils.class);
    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserService service = mock(UserService.class);
    private final UserDetails userDetails = mock(UserDetails.class);
    private final Authentication authentication = mock(Authentication.class);
    private final AuthenticationController controller = new AuthenticationController(jwtUtils, authenticationManager, service);

    private final ArgumentCaptor<Authentication> authenticationCaptor = ArgumentCaptor.forClass(Authentication.class);

    @Test
    void should_return_valid_jwt_token_when_credentials_are_passed() {
        var token = UUID.randomUUID().toString();
        when(authenticationManager.authenticate(authenticationCaptor.capture())).thenReturn(authentication);
        when(service.loadUserByUsername(jwtRequest.username())).thenReturn(userDetails);
        when(jwtUtils.generateToken(userDetails)).thenReturn(token);

        var authenticationResponse = controller.authenticate(jwtRequest);
        assertNotNull(authenticationResponse);
        assertEquals(token, authenticationResponse.jwtToken());

        var authenticationCaptorValue = authenticationCaptor.getValue();
        verify(authenticationManager).authenticate(authenticationCaptorValue);
        verify(service).loadUserByUsername(jwtRequest.username());
        verify(jwtUtils).generateToken(userDetails);
    }

    @Test
    void should_throw_BadCredentialsException_when_authentication_fails() {
        when(authenticationManager.authenticate(authenticationCaptor.capture())).thenThrow(BadCredentialsException.class);

        assertThrows(BadCredentialsException.class, () -> controller.authenticate(jwtRequest));

        var authenticationCaptorValue = authenticationCaptor.getValue();
        verify(authenticationManager).authenticate(authenticationCaptorValue);
        verify(service, never()).loadUserByUsername(jwtRequest.username());
        verify(jwtUtils, never()).generateToken(userDetails);
    }
}