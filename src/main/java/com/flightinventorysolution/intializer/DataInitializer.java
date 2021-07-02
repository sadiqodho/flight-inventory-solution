package com.flightinventorysolution.intializer;

import com.flightinventorysolution.models.Role;
import com.flightinventorysolution.models.User;
import com.flightinventorysolution.repositories.RoleRepository;
import com.flightinventorysolution.repositories.UserRepository;
import com.flightinventorysolution.types.RoleType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent>{

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) return;
        try {
            // Admin Role
            createUserWithRoleIfNotFound("admin",
                    "admin@gmail.com",
                    "123456789",
                   "ROLE_ADMIN");

            // User Role
            createUserWithRoleIfNotFound("user",
                    "user@gmail.com",
                    "123456789",
                    "ROLE_USER");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional
    void createUserWithRoleIfNotFound(String username, String email, String password, String roleName) throws Exception {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Set.of(createRoleIfNotExists(roleName)));
            userRepository.save(user);
        }
    }

    @Transactional
    Role createRoleIfNotExists(String name){
        Role role = roleRepository.findByName(RoleType.valueOf(name));
        if(role == null){
            role = new Role();
            role.setName(RoleType.valueOf(name));
            role = roleRepository.save(role);
        }
        return role;
    }
}
