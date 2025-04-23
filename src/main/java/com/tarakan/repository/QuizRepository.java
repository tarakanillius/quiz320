package com.tarakan.repository;

import com.tarakan.model.Quiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Repository class for managing quizzes.
 * <p>
 * This class provides methods to save, retrieve, and delete quizzes.
 * It uses a HashMap to store quizzes, where the key is the quiz's ID.
 * </p>
 * @see Quiz
 */
public class QuizRepository {
    /**
     * Map to store quizzes.
     */
    private final Map<String, Quiz> quizzes;
    /**
     * Constructs a new QuizRepository.
     */
    public QuizRepository() {
        this.quizzes = new HashMap<>();
    }
    /**
     * Saves a quiz.
     * <p>
     * If a quiz with the same ID already exists, it will be overwritten.
     * </p>
     * @param quiz the quiz to be saved
     */
    public void save(Quiz quiz) {
        quizzes.put(quiz.getId(), quiz);
    }
    /**
     * Retrieves a quiz by its ID.
     * <p>
     * If no quiz with the given ID exists, null is returned.
     * </p>
     * @param id quiz ID to retrieve
     * @return the quiz with the given ID, or null if not found
     */
    public Quiz findById(String id) {
        return quizzes.get(id);
    }
    /**
     * Retrieves all quizzes.
     * <p>
     * Returns an empty list if no quizzes are found.
     * </p>
     * @return a list of all quizzes
     */
    public List<Quiz> findAll() {
        return new ArrayList<>(quizzes.values());
    }
    /**
     * Deletes a quiz by its ID.
     * <p>
     * If no quiz with the given ID exists, no action is taken.
     * </p>
     * @param id quiz ID to delete
     */
    public void delete(String id) {
        quizzes.remove(id);
    }
    /**
     * Saves a list of quizzes.
     * <p>
     * This method is used to save a list of quizzes to the repository.
     * It iterates over the list and calls the save method for each quiz.
     * </p>
     * @param quizList the list of quizzes to be saved
     */
    public void saveAll(List<Quiz> quizList) {
        for (Quiz quiz : quizList) save(quiz);
    }
}
