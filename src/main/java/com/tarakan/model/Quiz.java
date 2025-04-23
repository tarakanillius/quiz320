package com.tarakan.model;

import java.util.List;
import java.util.UUID;
/**
 * Represents a quiz with a unique ID, title, description, and a list of questions.
 * <p>
 * This class provides methods to get the quiz's ID, title, description, and questions.
 * It also provides a constructor to create a new quiz with a unique ID.
 * </p>
 */
public class Quiz {
    /**
     * Unique identifier for the quiz.
     */
    private String id;
    /**
     * Title of the quiz.
     */
    private String title;
    /**
     * Description of the quiz.
     */
    private String description;
    /**
     * List of questions in the quiz.
     */
    private List<Question> questions;
    /**
     * Empty constructor for serialization.
     * <p>
     * This constructor is required for serialization and deserialization.
     * It should not be used directly.
     * </p>
     */
    public Quiz() {}

    /**
     * Constructs a new Quiz.
     * <p>
     * This constructor initializes the quiz with a unique ID, title, description, and list of questions.
     * </p>
     * @param title The title of the quiz.
     * @param description The description of the quiz.
     * @param questions The list of questions in the quiz.
     */
    public Quiz(String title, String description, List<Question> questions) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.questions = questions;
    }
    /**
     * Gets the unique identifier for the quiz.
     * @return The unique identifier for the quiz.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the title of the quiz.
     * @return The title of the quiz.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the description of the quiz.
     * @return The description of the quiz.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the list of questions in the quiz.
     * @return The list of questions in the quiz.
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
