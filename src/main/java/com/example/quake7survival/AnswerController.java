package com.example.quake7survival;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.quake7survival.entity.Answer;
import com.example.quake7survival.repositories.AnswerRepository;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@RestController
public class AnswerController {
    private final AnswerRepository repository;

    public AnswerController(AnswerRepository repository){
        this.repository = repository;
    }

    @GetMapping("/answer/{id}")
    public Mono<Answer> getAnswerById(@PathVariable Integer id) {
        try {
            Optional<Answer> res = repository.findById(id);
            return Mono.justOrEmpty(res);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("データ取得に失敗しました", e));
        }
    }

    @PostConstruct
    public void init() {
        try {
            ClassPathResource cr = new ClassPathResource("csv/回答.csv");
            InputStream is = cr.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            boolean isFirst = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                String[] fields = line.split(",");
                Answer answer = new Answer();
                answer.setQuestionId(Integer.parseInt(fields[0]));
                answer.setExplanation(fields[1]);
                answer.setSurvivalRateA(Integer.parseInt(fields[2]));
                answer.setSurvivalRateB(Integer.parseInt(fields[3]));
                answer.setSurvivalRateC(Integer.parseInt(fields[4]));
                if (fields.length > 5) {
                    answer.setSurvivalRateD(Integer.parseInt(fields[5]));
                }
                repository.saveAndFlush(answer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV data", e);
        }
    }
}
