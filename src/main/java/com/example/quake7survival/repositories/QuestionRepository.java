package com.example.quake7survival.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quake7survival.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer>{
}

