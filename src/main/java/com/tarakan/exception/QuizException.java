package com.tarakan.exception;
/**
 * Exception class for quiz-related exceptions.
 * <p>
 *     This class extends the RuntimeException class and provides a constructor that takes an error message as a parameter.
 * </p>
 */
public class QuizException extends RuntimeException {
    /**
     * Constructs a new QuizException with the specified error message.
     * @param message the error message
     */
    public QuizException(String message) {
        super(message);
    }
}