package com.tarakan.cli;

import com.tarakan.service.QuizService;
import java.util.Scanner;
/**
 * Main application class for the CLI version of the Quiz Application.
 * <p>
 * This class is responsible for initializing the CLI application, handling user input,
 * and displaying the main menu. It uses the QuizService to interact with the quiz repository.
 * </p>
 */
public class CliApplication {
    /**
     * QuizService instance for interacting with the quiz repository.
     */
    private final CliInputHandler inputHandler;
    /**
     * CLI menu handler for handling user choices.
     */
    private final CliMenuHandler menuHandler;
    /**
     * Constructor for the CliApplication class.
     * <p>
     * Initializes the component with the quiz service and input handler.
     * </p>
     * @param quizService The quiz service for managing quizzes.
     */
    public CliApplication(QuizService quizService) {
        this.inputHandler = new CliInputHandler(new Scanner(System.in));
        this.menuHandler = new CliMenuHandler(quizService, inputHandler);
    }
    /**
     * Starts the CLI application.
     * <p>
     * This method displays the main menu and handles user choices until the user chooses to exit.
     * </p>
     */
    public void start() {
        boolean running = true;

        while (running) {
            displayMainMenu();

            int choice = inputHandler.promptPositiveInt("Please enter your choice: ");

            running = menuHandler.handleMainMenuChoice(choice);
        }

        inputHandler.close();

        System.out.println("Thank you for using the Quiz Application!");
    }
    /**
     * Displays the main menu.
     */
    private void displayMainMenu() {
        System.out.println("\n===== Quiz Application =====\n1. Create a new quiz\n2. List all quizzes\n3. Take a quiz\n4. Delete a quiz\n5. Exit");
    }
}
