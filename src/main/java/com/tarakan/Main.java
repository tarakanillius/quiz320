package com.tarakan;

import com.tarakan.repository.QuizRepository;
import com.tarakan.service.QuizService;
import com.tarakan.config.QuizDataInitializer;
import com.tarakan.cli.CliApplication;
import java.io.File;
/**
 * Main class to start the Quiz Application.
 */
public class Main {
    private static final String QUIZZES_FILE_PATH = "quizzes.json";
    /**
     * Main method to start the Quiz Application.
     * <p>
     * Initializes the quiz service and starts the CLI application.
     * If the quizzes file does not exist, it creates sample quizzes.
     * </p>
     * @param args command line arguments
     */
    public static void main(String[] args) {
        File file = new File(QUIZZES_FILE_PATH);
        if (!file.exists()) {
            System.out.println("No quizzes file found. Creating sample quizzes...");
            QuizDataInitializer.createQuizzesFile(QUIZZES_FILE_PATH);
        }
        QuizRepository quizRepository = new QuizRepository();
        QuizService quizService = new QuizService(quizRepository, QUIZZES_FILE_PATH);
        CliApplication cli = new CliApplication(quizService);
        cli.start();
    }
}
