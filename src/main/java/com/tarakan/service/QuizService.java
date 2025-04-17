package com.tarakan.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarakan.repository.QuizRepository;
import com.tarakan.config.CreateQuizzesFile;
import com.tarakan.exception.QuizException;
import com.tarakan.model.Quiz;
import java.io.IOException;
import java.util.List;
import java.io.File;

public class QuizService {
    private final QuizRepository quizRepository;
    private final ObjectMapper objectMapper;
    private final String QUIZZES_FILE_PATH = "src/main/resources/quizzes.json";

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadQuizzesFromFile();
    }

    public void createQuiz(Quiz quiz) throws QuizException {
        if (quiz.getTitle() == null || quiz.getTitle().isEmpty()) throw new QuizException("Quiz title cannot be empty");
        if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) throw new QuizException("Quiz must have at least one question");
        quizRepository.save(quiz);
        saveQuizzesToFile();
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(String id) throws QuizException {
        Quiz quiz = quizRepository.findById(id);
        if (quiz == null) throw new QuizException("Quiz not found with id: " + id);
        quizRepository.delete(id);
        saveQuizzesToFile();
    }

    private void loadQuizzesFromFile() {
        try {
            File file = new File(QUIZZES_FILE_PATH);
            if (file.exists()) {
                List<Quiz> quizzes = objectMapper.readValue(file, new TypeReference<>() {
                });
                quizRepository.saveAll(quizzes);
                System.out.println("Loaded " + quizzes.size() + " quizzes from file");
            } else {
                System.out.println("No quizzes file found. Creating sample quizzes...");
                CreateQuizzesFile.createQuizzesFile();

                if (file.exists()) {
                    List<Quiz> quizzes = objectMapper.readValue(file, new TypeReference<>() {
                    });
                    quizRepository.saveAll(quizzes);
                    System.out.println("Loaded " + quizzes.size() + " sample quizzes");
                } else {
                    System.out.println("Failed to create quizzes file. Starting with empty quiz list.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading quizzes from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
