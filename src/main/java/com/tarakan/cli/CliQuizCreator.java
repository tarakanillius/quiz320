package com.tarakan.cli;

import com.tarakan.model.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class for creating quizzes using the CLI.
 * <p>
 * This class provides methods to create a new quiz using the CLI.
 * It allows the user to add questions to the quiz and then creates the quiz.
 * The created quiz is returned as a Quiz object.
 * </p>
 */
public class CliQuizCreator {
    /**
     * Input handler for CLI interactions.
     */
    private final CliInputHandler inputHandler;
    /**
     * Constructor for CliQuizCreator.
     * <p>
     * This constructor initializes the CliQuizCreator with the provided CliInputHandler.
     * </p>
     * @param inputHandler Input handler for CLI interactions.
     */
    public CliQuizCreator(CliInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
    /**
     * Method to create a new quiz.
     * <p>
     * This method guides the user through the process of creating a new quiz.
     * It prompts the user to enter a title and description for the quiz.
     * Then, it allows the user to add questions to the quiz.
     * The user can choose to add multiple-choice questions, short answer questions, or exit the question creation process.
     * The created quiz is returned as a Quiz object.
     * </p>
     * @return The created quiz as a Quiz object.
     */
    public Quiz createQuiz() {
        System.out.println("\n===== Create a New Quiz =====");
        System.out.print("Enter quiz title: ");
        String title = inputHandler.getStringInput();
        System.out.print("Enter quiz description: ");
        String description = inputHandler.getStringInput();

        List<Question> questions = createQuestions();
        return new Quiz(title, description, questions);
    }
    /**
     * Method to create a list of questions for the quiz.
     * <p>
     * This method allows the user to add multiple-choice questions, true/false questions, or exit the question creation process.
     * It returns a list of questions created by the user.
     * </p>
     * @return A list of questions created by the user.
     */
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
    /**
     * Method to create a multiple-choice question.
     * <p>
     * This method guides the user through the process of creating a multiple-choice question.
     * It prompts the user to enter the question text, points, and answer options.
     * The user can add as many answer options as needed.
     * The created question is returned as a MultipleChoiceQuestion object.
     * </p>
     * @return The created multiple-choice question as a MultipleChoiceQuestion object.
     */
    private MultipleChoiceQuestion createMultipleChoiceQuestion() {
        System.out.print("Enter question text: ");
        String text = inputHandler.getStringInput();
        System.out.print("Enter points for this question: ");
        int points = inputHandler.getIntInput();

        List<String> options = new ArrayList<>();
        System.out.println("Enter options (enter 'done' when finished):");

        while (true) {
            System.out.print("Option " + (options.size() + 1) + ": ");
            String option = inputHandler.getStringInput();
            if (option.equalsIgnoreCase("done")) break;
            options.add(option);
        }

        System.out.print("Enter the index of the correct option (1-" + options.size() + "): ");
        int correctIndex = inputHandler.getIntInput() - 1;

        return new MultipleChoiceQuestion(text, points, options, correctIndex);
    }
    /**
     * Method to create a true/false question.
     * <p>
     * This method guides the user through the process of creating a true/false question.
     * It prompts the user to enter the question text and points.
     * The created question is returned as a TrueFalseQuestion object.
     * </p>
     * @return The created true/false question as a TrueFalseQuestion object.
     */
    private TrueFalseQuestion createTrueFalseQuestion() {
        System.out.print("Enter question text: ");
        String text = inputHandler.getStringInput();
        System.out.print("Enter points for this question: ");
        int points = inputHandler.getIntInput();
        System.out.print("Is the answer true? (yes/no): ");
        boolean correctAnswer = inputHandler.getBooleanInput("yes");

        return new TrueFalseQuestion(text, points, correctAnswer);
    }
}
