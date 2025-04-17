package com.tarakan.cli;

import com.tarakan.exception.QuizException;
import com.tarakan.service.QuizService;
import com.tarakan.model.*;
import java.util.Scanner;

public class CLIMenuHandler {
    private final QuizService quizService;
    private final QuizCreator quizCreator;
    private final QuizTaker quizTaker;
    private final QuizManager quizManager;

    public CLIMenuHandler(QuizService quizService, Scanner scanner) {
        this.quizService = quizService;
        this.quizCreator = new QuizCreator(scanner);
        this.quizTaker = new QuizTaker(scanner);
        this.quizManager = new QuizManager(quizService, scanner);
    }

    public boolean handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1: createQuiz(); return true;
            case 2: listQuizzes(); return true;
            case 3: takeQuiz(); return true;
            case 4: deleteQuiz(); return true;
            case 5: return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    private void createQuiz() {
        Quiz quiz = quizCreator.createQuiz();
        try {
            quizService.createQuiz(quiz);
            System.out.println("Quiz created successfully!");
        } catch (QuizException e) {
            System.out.println("Error creating quiz: " + e.getMessage());
        }
    }

    private void listQuizzes() {
        quizManager.listQuizzes();
    }

    private void takeQuiz() {
        Quiz quiz = quizManager.selectQuiz("take");
        if (quiz != null) {
            quizTaker.takeQuiz(quiz);
        }
    }

    private void deleteQuiz() {
        quizManager.deleteQuiz();
    }
}
