package com.example.quake7survival.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int questionId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "survival_rate_A", nullable = false)
    private int survivalRateA;

    @Column(name = "survival_rate_B", nullable = false)
    private int survivalRateB;

    @Column(name = "survival_rate_C", nullable = false)
    private int survivalRateC;

    @Column(name = "survival_rate_D")
    private int survivalRateD;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int id) {
        this.questionId = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getSurvivalRateA() {
        return survivalRateA;
    }

    public void setSurvivalRateA(int survivalRateA) {
        this.survivalRateA = survivalRateA;
    }

    public int getSurvivalRateB() {
        return survivalRateB;
    }

    public void setSurvivalRateB(int survivalRateB) {
        this.survivalRateB = survivalRateB;
    }

    public int getSurvivalRateC() {
        return survivalRateC;
    }

    public void setSurvivalRateC(int survivalRateC) {
        this.survivalRateC = survivalRateC;
    }

    public int getSurvivalRateD() {
        return survivalRateD;
    }

    public void setSurvivalRateD(int survivalRateD) {
        this.survivalRateD = survivalRateD;
    }
}
