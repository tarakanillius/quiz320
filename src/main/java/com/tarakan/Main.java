package com.tarakan;

import com.tarakan.config.QuizDataInitializer;
import com.tarakan.repository.QuizRepository;
import io.github.cdimascio.dotenv.Dotenv;
import com.tarakan.service.QuizService;
import com.tarakan.cli.CliApplication;
import java.io.File;
/**
 * Main class to start the Quiz Application.
 */
public class Main {
    /**
     * Dotenv instance for loading environment variables.
     * <p>
     *     This instance is used to load environment variables from the .env file.
     *     It is used to load the path to the quizzes file.
     * </p>
     */
    private static final Dotenv dotenv = Dotenv.load();
    private static final String QUIZZES_FILE_PATH = dotenv.get("QUIZZES_FILE_PATH");
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

        QuizService quizService = new QuizService(new QuizRepository(), QUIZZES_FILE_PATH);
        new CliApplication(quizService).start();
    }
}
