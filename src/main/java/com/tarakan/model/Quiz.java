package com.tarakan.model;

import org.bson.types.ObjectId;
import java.util.List;

public class Quiz {
    private ObjectId id;
    private String title;
    private String description;
    private List<Question> questions;
    private int timeLimit;

    public Quiz(){}
    
    public Quiz(String title, String description, List<Question> questions, int timeLimit) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.timeLimit = timeLimit;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public int getTimeLimit() {
        return timeLimit;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", timeLimit=" + timeLimit +
                '}';
    }
}
