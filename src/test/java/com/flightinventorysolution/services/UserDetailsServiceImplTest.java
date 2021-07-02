package com.flightinventorysolution.services;

import com.flightinventorysolution.models.Role;
import com.flightinventorysolution.models.User;
import com.flightinventorysolution.repositories.UserRepository;
import com.flightinventorysolution.types.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private User user;

    @BeforeEach
    void before(){
        user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword("123456789");
        user.setRoles(Set.of(new Role(RoleType.ROLE_ADMIN)));
    }

    @Test
    void positive_loadUserByUsernameTest(){
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        Assertions.assertThat(userDetails.getUsername()).isEqualTo("admin");

    }
}
