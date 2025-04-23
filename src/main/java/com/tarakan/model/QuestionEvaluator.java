package com.tarakan.model;
/**
 * Interface for evaluating the correctness of a response to a question.
 * <p>
 * This interface defines a method for evaluating the correctness of a response
 * to a question and a method for getting the number of points awarded for
 * answering the question correctly.
 * </p>
 */
public interface QuestionEvaluator {
    /**
     * Evaluates the correctness of a response to a question.
     * @param response the response to evaluate
     * @return true if the response is correct, false otherwise
     */
    boolean evaluate(Object response);
    /**
     * Gets the number of points awarded for answering the question correctly.
     * @return the number of points awarded for answering the question correctly
     */
    int getPoints();
}