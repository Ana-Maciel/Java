package com.example.jogo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jogo.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

}