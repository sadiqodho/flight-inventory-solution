package com.flightinventorysolution.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootTest
public class AuthEntryPointJwtTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AuthenticationException authenticationException;
    private AuthEntryPointJwt auth;

    @BeforeEach
    void before(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        authenticationException  = Mockito.mock(AuthenticationException.class);
        auth = Mockito.mock(AuthEntryPointJwt.class);
    }

    @Test
    void negative_commenceTest() throws ServletException, IOException {
        auth.commence(request, response, authenticationException);
        Mockito.verify(auth, Mockito.times(1))
                .commence(request, response, authenticationException);
    }
}
