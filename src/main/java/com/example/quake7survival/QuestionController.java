package com.example.quake7survival;

import org.springframework.web.bind.annotation.RestController;

import com.example.quake7survival.entity.Question;
import com.example.quake7survival.repositories.QuestionRepository;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class QuestionController {
    private final QuestionRepository repository;

    public QuestionController(QuestionRepository repository){
        this.repository = repository;
    }

    @GetMapping("/question/{id}")
    public Mono<Question> getQuestionById(@PathVariable Integer id) {
        try {
            Optional<Question> res = repository.findById(id);
            return Mono.justOrEmpty(res);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("データ取得に失敗しました", e));
        }
    }
    
    @PostConstruct
    public void init() {
    try {
        ClassPathResource cr = new ClassPathResource("csv/質問.csv");
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
            Question question = new Question();
            question.setTitle(fields[0]);
            question.setText(fields[1]);
            question.setChoiceA(fields[2]);
            question.setChoiceB(fields[3]);
            question.setChoiceC(fields[4]);
            question.setChoiceD(fields.length > 5 ? fields[5] : "");
            repository.saveAndFlush(question);
        }
    } catch (IOException e) {
        throw new RuntimeException("Failed to load CSV data", e);
    }
}

}
