package com.example.jogo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jogo.User;

public interface UserRepository extends JpaRepository<User, Integer> {
 
    User findByUsername(String username);
}