package com.tarakan.cli;

import com.tarakan.service.QuizService;
import com.tarakan.model.Quiz;
import java.util.List;
/**
 * Class for managing quizzes in the CLI.
 * <p>
 * This class provides methods to list quizzes and select quizzes for taking or deleting.
 * It uses a QuizService to interact with the quiz repository.
 * </p>
 */
public class CliQuizManager {
    /**
     * QuizService instance for interacting with the quiz repository.
     */
    private final QuizService quizService;
    /**
     * Input handler for CLI interactions.
     */
    private final CliInputHandler inputHandler;
    /**
     * Constructor for CliQuizManager.
     * <p>
     * This constructor initializes the CliQuizManager with the provided QuizService and CliInputHandler.
     * </p>
     * @param quizService QuizService instance for interacting with the quiz repository.
     * @param inputHandler CliInputHandler instance for handling CLI input.
     */
    public CliQuizManager(QuizService quizService, CliInputHandler inputHandler) {
        this.quizService = quizService;
        this.inputHandler = inputHandler;
    }
    /**
     * Method to list all quizzes.
     * <p>
     * This method lists all quizzes available in the quiz repository.
     * It displays the title, description, and number of questions for each quiz.
     * </p>
     */
    public void listQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("\n===== Available Quizzes =====");

        for (int i = 0; i < quizzes.size(); i++) {
            Quiz quiz = quizzes.get(i);
            System.out.println((i + 1) + ". " + quiz.getTitle() + " - " + quiz.getDescription() + " (" + quiz.getQuestions().size() + " questions)");
        }

    }
    /**
     * Method to select a quiz for taking or deleting.
     * <p>
     * This method allows the user to select a quiz for either taking or deleting.
     * It displays a list of available quizzes and allows the user to select a quiz by entering its number.
     * If the user selects a quiz, the method returns the selected quiz.
     * If the user enters an invalid quiz number, an error message is displayed.
     * </p>
     * @param action The action to be performed (either "take" or "delete").
     * @return The selected quiz or null if the user enters an invalid quiz number.
     */
    public Quiz selectQuiz(String action) {
        List<Quiz> quizzes = quizService.getAllQuizzes();

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available to " + action + ".");
            return null;
        }

        int quizIndex = inputHandler.promptPositiveInt("Enter the number of the quiz you want to " + action + ": ") - 1;

        if (quizIndex < 0 || quizIndex > quizzes.size()+1) {
            System.out.println("Invalid quiz number.");

            return null;
        }

        return quizzes.get(quizIndex);
    }
    /**
     * Method to delete a quiz.
     * <p>
     * This method allows the user to delete a quiz.
     * After selecting a quiz, the user is prompted to confirm the deletion.
     * If the user confirms the deletion, the quiz is deleted from the repository.
     * If the user cancels the deletion, a message is displayed.
     * </p>
     */
    public void deleteQuiz() {
        Quiz quiz = selectQuiz("delete");

        if (quiz == null) return;

        System.out.print("Are you sure you want to delete '" + quiz.getTitle() + "'? (yes/no): ");

        if (inputHandler.getBooleanInput("yes")) {
            quizService.deleteQuiz(quiz.getId());

            System.out.println("Quiz deleted successfully!");

            return;
        }

        System.out.println("Deletion cancelled.");
    }
}
