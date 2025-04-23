package com.tarakan.cli;

import com.tarakan.model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class QuizCreator {
    private final Scanner scanner;
    private final InputHandler inputHandler;

    public QuizCreator(Scanner scanner) {
        this.scanner = scanner;
        this.inputHandler = new InputHandler(scanner);
    }

    public Quiz createQuiz() {
        System.out.println("\n===== Create a New Quiz =====");
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        System.out.print("Enter quiz description: ");
        String description = scanner.nextLine();
        System.out.print("Enter time limit (in minutes): ");
        int timeLimit = inputHandler.getIntInput();

        List<Question> questions = createQuestions();
        return new Quiz(title, description, questions, timeLimit);
    }

    private List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        boolean addingQuestions = true;

        while (addingQuestions) {
            System.out.println("\n===== Add a Question =====\n1. Multiple Choice Question\n2. True/False Question\n3. Finish adding questions");
            System.out.print("Enter your choice: ");

            int choice = inputHandler.getIntInput();
            if (choice == 1) questions.add(createMultipleChoiceQuestion());
            else if (choice == 2) questions.add(createTrueFalseQuestion());
            else if (choice == 3) addingQuestions = false;
            else System.out.println("Invalid choice. Please try again.");
        }

        return questions;
    }

    private MultipleChoiceQuestion createMultipleChoiceQuestion() {
        System.out.print("Enter question text: ");
        String text = scanner.nextLine();
        System.out.print("Enter points for this question: ");
        int points = inputHandler.getIntInput();

        List<String> options = new ArrayList<>();
        System.out.println("Enter options (enter 'done' when finished):");

        while (true) {
            System.out.print("Option " + (options.size() + 1) + ": ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("done")) break;
            options.add(option);
        }

        System.out.print("Enter the index of the correct option (1-" + options.size() + "): ");
        int correctIndex = inputHandler.getIntInput() - 1;

        return new MultipleChoiceQuestion(text, points, options, correctIndex);
    }

    private TrueFalseQuestion createTrueFalseQuestion() {
        System.out.print("Enter question text: ");
        String text = scanner.nextLine();
        System.out.print("Enter points for this question: ");
        int points = inputHandler.getIntInput();
        System.out.print("Is the answer true? (yes/no): ");
        boolean correctAnswer = scanner.nextLine().equalsIgnoreCase("yes");

        return new TrueFalseQuestion(text, points, correctAnswer);
    }
}
