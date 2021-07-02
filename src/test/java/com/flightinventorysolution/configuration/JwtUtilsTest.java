package com.flightinventorysolution.configuration;

import com.flightinventorysolution.models.Role;
import com.flightinventorysolution.models.User;
import com.flightinventorysolution.models.UserDetailsImpl;
import com.flightinventorysolution.types.RoleType;
import io.jsonwebtoken.SignatureException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@SpringBootTest
public class JwtUtilsTest {

    @MockBean
    private Authentication authentication;

    private User user;
    private UserDetailsImpl userDetails;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @BeforeEach
    void before(){
        user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword("123456789");
        user.setRoles(Set.of(new Role(RoleType.ROLE_ADMIN)));
        userDetails = UserDetailsImpl.build(user);
        jwtUtils = new JwtUtils();
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
    }


    @Test
    void positive_generateJwtTokenTest(){
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        Assertions.assertThat(jwtToken.length()).isEqualTo(174);
    }

    @Test
    void positive_getUserNameFromJwtTokenTest(){
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        Assertions.assertThat(jwtUtils.getUserNameFromJwtToken(jwtToken)).isEqualTo("admin");
    }

    @Test
    void positive_validateJwtTokenTest(){
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        Assertions.assertThat(jwtUtils.validateJwtToken(jwtToken)).isTrue();
    }

    @Test
    @ExceptionHandler(value = IllegalArgumentException.class)
    void emptyToken_validateJwtTokenTest(){
        JwtUtils jwtUtilsMock = Mockito.mock(JwtUtils.class);
        Mockito.doThrow(new IllegalArgumentException("JWT String argument cannot be null or empty."))
                .when(jwtUtilsMock).validateJwtToken("");
    }

    @Test
    @ExceptionHandler(value = SignatureException.class)
    void invalid_signature_validateJwtTokenTest(){
        JwtUtils jwtUtilsMock = Mockito.mock(JwtUtils.class);
        Mockito.doThrow(new SignatureException("Invalid Signature"))
                .when(jwtUtilsMock).validateJwtToken(
                        "eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjI1MTQ5MjIwLCJleHAiOjE2MjUyMzU2MjB9.JsuGAX4nxsSHw7V_vECTTkiXvAE2G_6b7w60PEFeSNPRdDb2t1g_fxvu4zlN-98j0anl0QVdqV9qEONtCQpCzA");
    }
}
