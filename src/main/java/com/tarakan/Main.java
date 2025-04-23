package com.tarakan;

import com.tarakan.repository.QuizRepository;
import com.tarakan.service.QuizService;
import com.tarakan.config.QuizDataInitializer;
import com.tarakan.cli.CliApplication;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("quizzes.json");
        if (!file.exists()) {
            System.out.println("No quizzes file found. Creating sample quizzes...");
            QuizDataInitializer.createQuizzesFile();
        }

        QuizRepository quizRepository = new QuizRepository();
        QuizService quizService = new QuizService(quizRepository);
        CliApplication cli = new CliApplication(quizService);
        cli.start();
    }
}
