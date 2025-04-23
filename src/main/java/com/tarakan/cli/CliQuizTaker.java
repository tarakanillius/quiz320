package com.tarakan.cli;

import com.tarakan.model.*;
import java.util.Scanner;
import java.util.List;

public class CliQuizTaker {
    private final Scanner scanner;
    private final CliInputHandler inputHandler;

    public CliQuizTaker(Scanner scanner) {
        this.scanner = scanner;
        this.inputHandler = new CliInputHandler(scanner);
    }

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

    private int handleTFQ(TrueFalseQuestion tfq) {
        System.out.print("Your answer (true/false): ");
        boolean answer = scanner.nextLine().equalsIgnoreCase("true");

        if (tfq.evaluate(answer)) {
            System.out.println("Correct!");
            return tfq.getPoints();
        } else {
            System.out.println("Incorrect. The correct answer was: " + tfq.isCorrectAnswer());
            return 0;
        }
    }

    private void displayResults(int earnedPoints, int totalPoints) {
        int percentage = totalPoints > 0 ? (earnedPoints * 100 / totalPoints) : 0;
        System.out.println("\n===== Quiz Complete =====\nYour score: " + earnedPoints + " out of " + totalPoints+"\nPercentage: " + percentage + "%");
    }
}
