package com.tomorrowproject.restaurante_api.config;

import com.tomorrowproject.restaurante_api.entity.Role;
import com.tomorrowproject.restaurante_api.entity.User;
import com.tomorrowproject.restaurante_api.exception.handler.CustomEntityResponseEntity;
import com.tomorrowproject.restaurante_api.repository.RoleRepository;
import com.tomorrowproject.restaurante_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@Transactional
public class AdminUserConfig implements CommandLineRunner {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findById(Role.values.ADMIN.getRoleId()).orElse(null);

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}
