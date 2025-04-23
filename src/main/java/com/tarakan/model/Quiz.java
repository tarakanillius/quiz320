package com.tarakan.model;

import java.util.List;
import java.util.UUID;

public class Quiz {
    private String id;
    private String title;
    private String description;
    private List<Question> questions;

    public Quiz() {}

    public Quiz(String title, String description, List<Question> questions) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
