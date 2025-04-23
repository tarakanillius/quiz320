package com.tarakan;

import com.tarakan.repository.QuizRepository;
import com.tarakan.config.CreateQuizzesFile;
import com.tarakan.service.QuizService;
import com.tarakan.cli.QuizCLI;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File quizzesFile = new File("quizzes.json");
        if (!quizzesFile.exists()) {
            System.out.println("Creating initial quizzes file...");
            CreateQuizzesFile.createQuizzesFile();
        }

        QuizRepository quizRepository = new QuizRepository();
        QuizService quizService = new QuizService(quizRepository);
        QuizCLI cli = new QuizCLI(quizService);
        cli.start();
    }
}
