package com.example.quake7survival;

import org.springframework.web.bind.annotation.RestController;

import com.example.quake7survival.entity.Question;
import com.example.quake7survival.repositories.QuestionRepository;

import reactor.core.publisher.Mono;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class QuestionController {
    private final QuestionRepository repository;

    public QuestionController(QuestionRepository repository){
        this.repository = repository;
    }

    @GetMapping("/question/{id}")
    public Mono<Question> getMethodName(@PathVariable Integer id) {
        try {
            Optional<Question> res = repository.findById(id);
            return Mono.justOrEmpty(res);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("データ取得に失敗しました", e));
        }
    }
    
    
}
