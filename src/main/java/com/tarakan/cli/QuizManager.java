package com.tarakan.cli;

import com.tarakan.exception.QuizException;
import com.tarakan.service.QuizService;
import com.tarakan.model.Quiz;
import java.util.Scanner;
import java.util.List;

public class QuizManager {
    private final QuizService quizService;
    private final Scanner scanner;
    private final InputHandler inputHandler;

    public QuizManager(QuizService quizService, Scanner scanner) {
        this.quizService = quizService;
        this.scanner = scanner;
        this.inputHandler = new InputHandler(scanner);
    }

    public void listQuizzes() {
        System.out.println("\n===== Available Quizzes =====");
        List<Quiz> quizzes = quizService.getAllQuizzes();

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        for (int i = 0; i < quizzes.size(); i++) {
            Quiz quiz = quizzes.get(i);
            System.out.println((i + 1) + ". " + quiz.getTitle() + " - " + quiz.getDescription() + " (" + quiz.getQuestions().size() + " questions)");
        }
    }

    public Quiz selectQuiz(String action) {
        List<Quiz> quizzes = quizService.getAllQuizzes();

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available to " + action + ".");
            return null;
        }

        System.out.print("Enter the number of the quiz you want to " + action + ": ");
        int quizIndex = inputHandler.getIntInput() - 1;

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz number.");
            return null;
        }

        return quizzes.get(quizIndex);
    }

    public void deleteQuiz() {
        Quiz quiz = selectQuiz("delete");
        if (quiz == null) return;

        System.out.print("Are you sure you want to delete '" + quiz.getTitle() + "'? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            try {
                quizService.deleteQuiz(quiz.getId());
                System.out.println("Quiz deleted successfully!");
            } catch (QuizException e) {
                System.out.println("Error deleting quiz: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
