package com.tarakan.repository;

import com.tarakan.model.Quiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizRepository {
    private final Map<String, Quiz> quizzes;

    public QuizRepository() {
        this.quizzes = new HashMap<>();
    }

    public void save(Quiz quiz) {
        quizzes.put(quiz.getId(), quiz);
    }

    public Quiz findById(String id) {
        return quizzes.get(id);
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(quizzes.values());
    }

    public void delete(String id) {
        quizzes.remove(id);
    }

    public void saveAll(List<Quiz> quizList) {
        for (Quiz quiz : quizList) save(quiz);
    }
}
