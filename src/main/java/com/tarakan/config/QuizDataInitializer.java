package com.tarakan.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarakan.model.MultipleChoiceQuestion;
import com.tarakan.model.TrueFalseQuestion;
import com.tarakan.model.Quiz;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;
/**
 * Class responsible for initializing quiz data.
 * <p>
 * This class provides methods to create sample quizzes and save them to a JSON file.
 * The quizzes are created using the MultipleChoiceQuestion and TrueFalseQuestion classes.
 * The created quizzes are then saved to a JSON file named "quizzes.json".
 * </p>
 * @see MultipleChoiceQuestion
 * @see TrueFalseQuestion
 * @see Quiz
 */
public class QuizDataInitializer {
    /**
     * Path to the file where quizzes will be saved.
     */
    private static final String QUIZZES_FILE_PATH = "quizzes.json";
    /**
     * Creates sample quizzes and saves them to a JSON file.
     * <p>
     * This method creates two sample quizzes: one for Java and one for general knowledge.
     * Each quiz contains multiple-choice and true/false questions.
     * </p>
     */
    public static void createQuizzesFile() {
        try {
            List<Quiz> quizzes = Arrays.asList(createJavaQuiz(), createGeneralKnowledgeQuiz());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File file = new File(QUIZZES_FILE_PATH);
            objectMapper.writeValue(file, quizzes);

            System.out.println("Successfully created quizzes.json with " + quizzes.size() + " sample quizzes");
        } catch (IOException e) {
            System.err.println("Error creating quizzes.json file: " + e.getMessage());
        }
    }
    /**
     * Creates a quiz for Java programming language.
     * @return A Quiz object representing a quiz for Java programming language.
     */
    private static Quiz createJavaQuiz() {
        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(
                "What is the main method signature in Java?",
                5,
                Arrays.asList("public static void main(String[] args)", "public void main(String[] args)", "public static void main()", "void main(String args[])"),
                0
        );
        TrueFalseQuestion tfq1 = new TrueFalseQuestion(
                "Java is a purely object-oriented language.",
                3,
                false
        );
        MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion(
                "Which of the following is not a Java keyword?",
                4,
                Arrays.asList("static", "finally", "super", "foreach"),
                3
        );
        return new Quiz(
                "Java Programming Basics",
                "Test your knowledge of Java programming fundamentals",
                Arrays.asList(mcq1, tfq1, mcq2)
        );
    }
    /**
     * Creates a general knowledge quiz.
     * @return A Quiz object representing a general knowledge quiz.
     */
    private static Quiz createGeneralKnowledgeQuiz() {
        MultipleChoiceQuestion mcq3 = new MultipleChoiceQuestion(
                "What is the capital of France?",
                2,
                Arrays.asList("London", "Berlin", "Paris", "Madrid"),
                2
        );
        TrueFalseQuestion tfq2 = new TrueFalseQuestion(
                "The Great Wall of China is visible from space.",
                2,
                false
        );
        MultipleChoiceQuestion mcq4 = new MultipleChoiceQuestion(
                "Who wrote 'Romeo and Juliet'?",
                3,
                Arrays.asList("Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"),
                1
        );
        return new Quiz(
                "General Knowledge",
                "Test your general knowledge with these questions",
                Arrays.asList(mcq3, tfq2, mcq4)
        );
    }
}
