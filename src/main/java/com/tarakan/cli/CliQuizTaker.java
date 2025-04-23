package com.tarakan.cli;

import com.tarakan.model.*;
import java.util.List;
/**
 * Class for handling quiz taking in the CLI.
 * <p>
 * This class provides methods to take a quiz in the CLI.
 * It allows users to answer questions and calculates the score.
 * It also provides methods to display the quiz title, description, and questions.
 * </p>
 */
public class CliQuizTaker {
    /**
     * Input handler for handling user input in the CLI.
     */
    private final CliInputHandler inputHandler;
    /**
     * Constructor for the CliQuizTaker class.
     * <p>
     * Initializes the component with the input handler for CLI interactions.
     * </p>
     * @param inputHandler The input handler for handling user input in the CLI.
     */
    public CliQuizTaker(CliInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
    /**
     * Method to take a quiz in the CLI.
     * <p>
     * This method takes a quiz in the CLI.
     * It displays the quiz title, description, and questions.
     * It allows users to answer questions and calculates the score.
     * </p>
     * @param quiz The quiz to be taken.
     */
    public void takeQuiz(Quiz quiz) {
        System.out.println("\n===== Taking Quiz: " + quiz.getTitle() + " =====");
        System.out.println("Description: " + quiz.getDescription());

        int totalPoints = 0, earnedPoints = 0;
        List<Question> questions = quiz.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            totalPoints += question.getPoints();

            System.out.println("\nQuestion " + (i + 1) + ": " + question.getText());
            if (question instanceof MultipleChoiceQuestion) {
                earnedPoints += handleMCQ((MultipleChoiceQuestion) question);
            } else if (question instanceof TrueFalseQuestion) {
                earnedPoints += handleTFQ((TrueFalseQuestion) question);
            }
        }

        displayResults(earnedPoints, totalPoints);
    }
    /**
     * Method to handle a multiple-choice question in the CLI.
     * <p>
     * This method handles a multiple-choice question in the CLI.
     * It displays the options for the question and allows the user to select an answer.
     * It returns the points earned for the multiple-choice question.
     * </p>
     * @param mcq The multiple-choice question to be handled.
     * @return The points earned for the multiple-choice question.
     */
    private int handleMCQ(MultipleChoiceQuestion mcq) {
        List<String> options = mcq.getOptions();
        for (int j = 0; j < options.size(); j++) {
            System.out.println((j + 1) + ". " + options.get(j));
        }

        System.out.print("Your answer (1-" + options.size() + "): ");
        int answer = inputHandler.getIntInput() - 1;

        if (mcq.evaluate(answer)) {
            System.out.println("Correct!");
            return mcq.getPoints();
        } else {
            System.out.println("Incorrect. The correct answer was: " + (mcq.getCorrectOptionIndex()));
            return 0;
        }
    }
    /**
     * Method to handle a true/false question in the CLI.
     * <p>
     * This method handles a true/false question in the CLI.
     * It displays the question and allows the user to select an answer.
     * It returns the points earned for the true/false question.
     * </p>
     * @param tfq The true/false question to be handled.
     * @return The points earned for the true/false question.
     */
    private int handleTFQ(TrueFalseQuestion tfq) {
        System.out.print("Your answer (true/false): ");
        boolean answer = inputHandler.getBooleanInput("true");

        if (tfq.evaluate(answer)) {
            System.out.println("Correct!");
            return tfq.getPoints();
        } else {
            System.out.println("Incorrect. The correct answer was: " + tfq.isCorrectAnswer());
            return 0;
        }
    }
    /**
     * Method to display the results of a quiz.
     * <p>
     * This method displays the results of a quiz.
     * It displays the earned points and the total points.
     * It also displays the percentage of points earned.
     * </p>
     * @param earnedPoints The earned points for the quiz.
     * @param totalPoints The total points for the quiz.
     */
    private void displayResults(int earnedPoints, int totalPoints) {
        int percentage = totalPoints > 0 ? (earnedPoints * 100 / totalPoints) : 0;
        System.out.println("\n===== Quiz Complete =====\nYour score: " + earnedPoints + " out of " + totalPoints+"\nPercentage: " + percentage + "%");
    }
}
