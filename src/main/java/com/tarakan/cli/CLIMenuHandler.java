package com.tarakan.cli;

import com.tarakan.exception.QuizException;
import com.tarakan.service.QuizService;
import com.tarakan.model.*;
/**
 * CLI menu handler for the quiz application.
 * <p>
 * This class provides methods to handle user choices from the main menu and delegate
 * to the appropriate methods for creating, listing, taking, and deleting quizzes.
 * </p>
 */
public class CliMenuHandler {
    /**
     * Quiz service for managing quizzes.
     */
    private final QuizService quizService;
    /**
     * CLI quiz creator for creating quizzes.
     */
    private final CliQuizCreator quizCreator;
    /**
     * CLI quiz taker for taking quizzes.
     */
    private final CliQuizTaker quizTaker;
    /**
     * CLI quiz manager for managing quizzes.
     */
    private final CliQuizManager quizManager;
    /**
     * Constructor for the CliMenuHandler class.
     * <p>
     * Initializes the component with the quiz service, quiz creator, quiz taker, and quiz manager.
     * </p>
     * @param quizService The quiz service for managing quizzes.
     * @param inputHandler The input handler for handling user input in the CLI.
     */
    public CliMenuHandler(QuizService quizService, CliInputHandler inputHandler) {
        this.quizManager = new CliQuizManager(quizService, inputHandler);
        this.quizCreator = new CliQuizCreator(inputHandler);
        this.quizTaker = new CliQuizTaker(inputHandler);
        this.quizService = quizService;
    }
    /**
     * Handles the main menu choice and delegates to the appropriate method.
     * <p>
     * This method handles the main menu choice and delegates to the appropriate method
     * for creating, listing, taking, and deleting quizzes.
     * </p>
     * @param choice The user's choice from the main menu.
     * @return true if the main menu should be displayed again, false otherwise.
     */
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
    /**
     * Creates a quiz using the quiz creator.
     * <p>
     * This method creates a quiz using the quiz creator and handles any errors that may occur.
     * </p>
     */
    private void createQuiz() {
        Quiz quiz = quizCreator.createQuiz();

        try {
            quizService.createQuiz(quiz);
            System.out.println("Quiz created successfully!");
        } catch (QuizException e) {
            System.out.println("Error creating quiz: " + e.getMessage());
        }
    }
    /**
     * Lists all quizzes.
     * <p>
     * This method lists all quizzes using the quiz manager.
     * </p>
     */
    private void listQuizzes() {
        quizManager.listQuizzes();
    }
    /**
     * Takes a quiz using the quiz taker.
     * <p>
     * This method takes a quiz using the quiz taker and handles any errors that may occur.
     * </p>
     */
    private void takeQuiz() {
        Quiz quiz = quizManager.selectQuiz("take");

        if (quiz != null) {
            quizTaker.takeQuiz(quiz);
        }
    }
    /**
     * Deletes a quiz using the quiz manager.
     * <p>
     * This method deletes a quiz using the quiz manager and handles any errors that may occur.
     * </p>
     */
    private void deleteQuiz() {
        quizManager.deleteQuiz();
    }
}
