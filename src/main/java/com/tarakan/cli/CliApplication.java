package com.tarakan.cli;

import com.tarakan.service.QuizService;
import java.util.Scanner;

public class CliApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final CliInputHandler inputHandler;
    private final CliMenuHandler menuHandler;

    public CliApplication(QuizService quizService) {
        this.inputHandler = new CliInputHandler(scanner);
        this.menuHandler = new CliMenuHandler(quizService, scanner);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = inputHandler.getIntInput();
            running = menuHandler.handleMainMenuChoice(choice);
        }
        scanner.close();
        System.out.println("Thank you for using the Quiz Application!");
    }

    private void displayMainMenu() {
        System.out.println("\n===== Quiz Application =====\n1. Create a new quiz\n2. List all quizzes\n3. Take a quiz\n4. Delete a quiz\n5. Exit");
        System.out.print("Enter your choice: ");
    }
}
