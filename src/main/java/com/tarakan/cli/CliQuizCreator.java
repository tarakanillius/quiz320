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

        String title = inputHandler.promptNonEmptyString("Enter quiz title: ");
        String description = inputHandler.promptNonEmptyString("Enter quiz description: ");

        List<Question> questions = createQuestions();

        if (questions == null || questions.isEmpty()) {
            System.out.println("No valid questions added. Quiz creation cancelled.");
            return null;
        }

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

        while (true) {
            System.out.println("\n===== Add a Question =====\n1. Multiple Choice Question\n2. True/False Question\n3. Finish adding questions");

            int choice = inputHandler.promptPositiveInt("Enter your choice: ");
            if (choice == 1) questions.add(createMultipleChoiceQuestion());
            else if (choice == 2) questions.add(createTrueFalseQuestion());
            else if (choice == 3) break;
            else System.out.println("Invalid choice. Please try again.");
        }

        if(!questions.isEmpty()) return questions;

        System.out.println("No questions added. Quiz creation cancelled.");

        return null;
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
        String text = inputHandler.promptNonEmptyString("Enter question text: ");
        int points = inputHandler.promptPositiveInt("Enter points for this question: ");
        List<String> options = new ArrayList<>();

        System.out.println("Enter options (enter 'done' when finished):");

        while (true) {
            String option = inputHandler.promptNonEmptyString("Option " + (options.size() + 1) + ": ");
            if (option.equalsIgnoreCase("done")) break;
            options.add(option);
        }

        int correctIndex = inputHandler.promptBoundedInt("Enter the index of the correct option (1-" + options.size() + "): ", 1, options.size()) - 1;

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
        String text = inputHandler.promptNonEmptyString("Enter question text: ");
        int points = inputHandler.promptPositiveInt("Enter points for this question: ");

        System.out.print("Is the answer true? (yes/no): ");
        boolean correctAnswer = inputHandler.getBooleanInput("yes");

        return new TrueFalseQuestion(text, points, correctAnswer);
    }
}
