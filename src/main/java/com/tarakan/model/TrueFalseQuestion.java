package com.tarakan.model;

import com.tarakan.exception.QuizException;
/**
 * Represents a true/false question in a quiz.
 * <p>
 * This class extends the Question class and provides additional functionality
 * for evaluating the correctness of a response.
 * </p>
 * @see Question
 * @see MultipleChoiceQuestion
 */
public class TrueFalseQuestion extends Question {
    /**
     * The correct answer for this true/false question.
     */
    private boolean correctAnswer;
    /**
     * Empty constructor for serialization.
     * <p>
     * This constructor is required for serialization and deserialization.
     * It should not be used directly.
     * </p>
     */
    public TrueFalseQuestion() {}
    /**
     * Constructs a new TrueFalseQuestion.
     * <p>
     * This constructor initializes the question text, points, and correct answer.
     * </p>
     * @param text The text of the question.
     * @param points The number of points awarded for answering the question correctly.
     * @param correctAnswer The correct answer for this question.
     */
    public TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }
    /**
     * Gets the correct answer for this true/false question.
     * @return The correct answer for this question.
     */
    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
    /**
     * Evaluates the correctness of a response to this true/false question.
     * <p>
     * This method checks if the provided response matches the correct answer.
     * </p>
     * @param response The response to evaluate.
     * @return True if the response matches the correct answer, false otherwise.
     */
    @Override
    public boolean evaluate(Object response) {
        if (!(response instanceof Boolean)) throw new QuizException("Response must be a boolean.");

        return ((Boolean) response) == correctAnswer;
    }
}
