package com.exam.project.config;

import com.exam.project.entity.Role;
import com.exam.project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ADMIN","ADMIN ACCOUNT"));
            roleRepository.save(new Role("USER","USER ACCOUNT"));
            System.out.println("Initial roles data inserted.");
        }
    }
}
