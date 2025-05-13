package com.tarakan.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
 * Abstract class representing a question.
 * <p>
 * This class defines the common properties and methods for all types of questions.
 * It provides a constructor for initializing the question text and points, and getter methods for accessing the question text and points.
 * It also implements the QuestionEvaluator interface, which defines methods for evaluating the correctness of a response
 * to a question and getting the number of points awarded for answering the question correctly.
 * </p>
 * <p>
 * JSON type and subtype information is specified to allow Jackson to correctly serialize and deserialize
 * objects of the Question class and its subclasses.
 * </p>
 * @see QuestionEvaluator
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MultipleChoiceQuestion"),
        @JsonSubTypes.Type(value = TrueFalseQuestion.class, name = "TrueFalseQuestion")
})
public abstract class Question implements QuestionEvaluator {
    /**
     * The text of the question.
     */
    protected String text;
    /**
     * The number of points awarded for answering the question correctly.
     */
    protected int points;
    /**
     * Empty constructor for serialization.
     * <p>
     * This constructor is required for serialization and deserialization.
     * It should not be used directly.
     * </p>
     * */
    public Question() {}
    /**
     * Constructs a new Question with the given text and points.
     * <p>
     * This constructor initializes the question text and points.
     * </p>
     * @param text  The text of the question.
     * @param points  The number of points awarded for answering the question correctly.
     */
    public Question(String text, int points) {
        if (points < 1) throw new IllegalArgumentException("Points should be greater than 0");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        this.text = text;
        this.points = points;
    }
    /**
     * Gets the text of the question.
     * @return The text of the question.
     */
    public String getText() {
        return text;
    }
    /**
     * Gets the number of points awarded for answering the question correctly.
     * @return The number of points awarded for answering the question correctly.
     */
    @Override
    public int getPoints() {
        return points;
    }
}
