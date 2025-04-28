package com.tarakan.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarakan.repository.QuizRepository;
import com.tarakan.exception.QuizException;
import com.tarakan.model.Quiz;
import java.io.IOException;
import java.util.List;
import java.io.File;
/**
 * Service class responsible for managing quiz operations.
 * <p>
 * This service provides methods for creating, retrieving, and deleting quizzes.
 * It also handles persistence by saving quizzes to and loading them from a JSON file.
 * </p>
 * @see QuizRepository
 * @see Quiz
 */
public class QuizService {
    /**
     * Repository for quiz data operations.
     */
    private final QuizRepository quizRepository;
    /**
     * JSON mapper for serializing and deserializing quiz data.
     */
    private final ObjectMapper objectMapper;
    /**
     * Path to the file where quizzes are persisted.
     */
    private final String QUIZZES_FILE_PATH = "quizzes.json";
    /**
     * Constructs a new QuizService with the specified repository.
     * <p>
     * Initializes the JSON object mapper and loads any existing quizzes from file.
     * </p>
     *
     * @param quizRepository the repository used for quiz data operations
     */
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadQuizzesFromFile();
    }
    /**
     * Creates a new quiz and persists it.
     * <p>
     * Validates that the quiz has a title and at least one question before saving.
     * </p>
     *
     * @param quiz the quiz to be created
     * @throws QuizException if the quiz is invalid (missing title or questions)
     */
    public void createQuiz(Quiz quiz) throws QuizException {
        if (quiz.getTitle().isEmpty() || quiz.getQuestions().isEmpty()) throw new QuizException("Quiz must have a title and at least one question");

        quizRepository.save(quiz);
        saveQuizzesToFile();
    }
    /**
     * Retrieves all quizzes from the repository.
     *
     * @return a list of all quizzes
     */
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    /**
     * Deletes a quiz with the specified ID.
     *
     * @param id the ID of the quiz to delete
     * @throws QuizException if no quiz with the specified ID exists
     */
    public void deleteQuiz(String id) throws QuizException {
        Quiz quiz = quizRepository.findById(id);
        if (quiz == null) throw new QuizException("Quiz not found with id: " + id);

        quizRepository.delete(id);
        saveQuizzesToFile();
    }
    /**
     * Loads quizzes from the JSON file into the repository.
     * <p>
     * If the file exists, deserializes the quizzes and adds them to the repository.
     * If the file doesn't exist, logs a message and starts with an empty repository.
     * </p>
     */
    private void loadQuizzesFromFile() {
        try {
            File file = new File(QUIZZES_FILE_PATH);

            if (file.exists()) {
                List<Quiz> quizzes = objectMapper.readValue(file, new TypeReference<>(){});
                quizRepository.saveAll(quizzes);
                System.out.println("Loaded " + quizzes.size() + " quizzes from file");
            } else {
                System.out.println("Failed to read quizzes file. Starting with an empty file.");
            }
        } catch (IOException e) {
            System.err.println("Error reading quizzes file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Saves all quizzes from the repository to the JSON file.
     * <p>
     * Serializes the quizzes with indented output for better readability.
     * </p>
     */
    private void saveQuizzesToFile() {
        try {
            List<Quiz> quizzes = quizRepository.findAll();
            objectMapper.writeValue(new File(QUIZZES_FILE_PATH), quizzes);
            System.out.println("Saved " + quizzes.size() + " quizzes to file");
        } catch (IOException e) {
            System.err.println("Error saving quizzes to file: " + e.getMessage());
        }
    }
}
