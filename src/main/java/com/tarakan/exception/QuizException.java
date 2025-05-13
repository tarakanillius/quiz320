package com.tarakan.exception;
/**
 * Exception class for handling quiz-related errors.
 * <p>
 * This class extends the built-in Exception class and provides a constructor
 * to create a new QuizException with a custom error message.
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