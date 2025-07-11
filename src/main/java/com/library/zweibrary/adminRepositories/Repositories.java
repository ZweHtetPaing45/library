package com.library.zweibrary.adminRepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.zweibrary.LoginData.LoginAdmin;

@Repository
public interface Repositories extends JpaRepository<LoginAdmin, Long> {
    Optional<LoginAdmin> findByUsername(String username);
}
