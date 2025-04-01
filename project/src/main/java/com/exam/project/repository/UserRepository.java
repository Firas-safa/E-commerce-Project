package com.exam.project.repository;

import com.exam.project.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUserByUserName(String userName);

    boolean existsByUserName(String userName);
}
