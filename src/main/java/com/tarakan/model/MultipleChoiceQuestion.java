package com.tarakan.model;

import java.util.List;
/**
 * Represents a multiple-choice question with a text, points, options, and a correct option.
 * <p>
 * This class extends the Question class and implements the QuestionEvaluator interface.
 * It provides methods to get the options and the correct option index.
 * It also implements the evaluate method from the QuestionEvaluator interface.
 * </p>
 * @see Question
 * @see QuestionEvaluator
 */
public class MultipleChoiceQuestion extends Question implements QuestionEvaluator {
    /**
     * The list of options for this multiple-choice question.
     */
    private List<String> options;
    /**
     * The index of the correct option for this multiple-choice question.
     */
    private int correctOptionIndex;
    /**
     * Empty constructor for serialization.
     * <p>
     * This constructor is required for serialization and deserialization.
     * It should not be used directly.
     * </p>
     */
    public MultipleChoiceQuestion() {}
    /**
     * Constructs a new MultipleChoiceQuestion.
     * <p>
     * This constructor initializes the question text, points, options, and correct option index.
     * </p>
     * @param text The text of the question.
     * @param points The number of points awarded for answering the question correctly.
     * @param options The list of options for this question.
     * @param correctOptionIndex The index of the correct option for this question.
     */
    public MultipleChoiceQuestion(String text, int points, List<String> options, int correctOptionIndex) {
        super(text, points);
        if (correctOptionIndex < 0 || correctOptionIndex > options.size()) throw new IllegalArgumentException("Invalid correct option index");
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    /**
     * Gets the list of options for this multiple-choice question.
     * @return The list of options for this question.
     */
    public List<String> getOptions() {
        return options;
    }
    /**
     * Gets the index of the correct option for this multiple-choice question.
     * @return The index of the correct option for this question.
     */
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
    /**
     * Evaluates the correctness of a response to this multiple-choice question.
     * @param response the response to evaluate
     * @return true if the response is correct, false otherwise
     */
    @Override
    public boolean evaluate(Object response) {
        if (!(response instanceof Integer)) return false;

        return ((Integer) response) == correctOptionIndex;
    }
}
