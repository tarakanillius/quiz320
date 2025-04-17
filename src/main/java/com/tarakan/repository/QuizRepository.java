package com.tarakan.repository;

import com.tarakan.model.Quiz;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizRepository {
    private final Map<ObjectId, Quiz> quizzes;

    public QuizRepository() {
        this.quizzes = new HashMap<>();
    }

    public void save(Quiz quiz) {
        if (quiz.getId() == null) quiz.setId(new ObjectId());
        quizzes.put(quiz.getId(), quiz);
    }

    public Quiz findById(ObjectId id) {
        return quizzes.get(id);
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(quizzes.values());
    }

    public void delete(ObjectId id) {
        quizzes.remove(id);
    }

    public void saveAll(List<Quiz> quizList) {
        for (Quiz quiz : quizList) save(quiz);
    }
}
