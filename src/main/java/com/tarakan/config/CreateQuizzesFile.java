package com.tarakan.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarakan.model.MultipleChoiceQuestion;
import com.tarakan.model.TrueFalseQuestion;
import com.tarakan.model.Quiz;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.io.File;

public class CreateQuizzesFile {
    private static final String QUIZZES_FILE_PATH = "quizzes.json";

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

    private static Quiz createJavaQuiz() {
        MultipleChoiceQuestion mcq1 = new MultipleChoiceQuestion(
                "What is the main method signature in Java?",
                5,
                Arrays.asList(
                        "public static void main(String[] args)",
                        "public void main(String[] args)",
                        "public static void main()",
                        "void main(String args[])"
                ),
                0
        );
        mcq1.setId(UUID.randomUUID().toString());

        TrueFalseQuestion tfq1 = new TrueFalseQuestion(
                "Java is a purely object-oriented language.",
                3,
                false
        );
        tfq1.setId(UUID.randomUUID().toString());

        MultipleChoiceQuestion mcq2 = new MultipleChoiceQuestion(
                "Which of the following is not a Java keyword?",
                4,
                Arrays.asList(
                        "static",
                        "finally",
                        "super",
                        "foreach"
                ),
                3
        );
        mcq2.setId(UUID.randomUUID().toString());

        return new Quiz(
                "Java Programming Basics",
                "Test your knowledge of Java programming fundamentals",
                Arrays.asList(mcq1, tfq1, mcq2),
                15
        );
    }

    private static Quiz createGeneralKnowledgeQuiz() {
        MultipleChoiceQuestion mcq3 = new MultipleChoiceQuestion(
                "What is the capital of France?",
                2,
                Arrays.asList(
                        "London",
                        "Berlin",
                        "Paris",
                        "Madrid"
                ),
                2
        );
        mcq3.setId(UUID.randomUUID().toString());

        TrueFalseQuestion tfq2 = new TrueFalseQuestion(
                "The Great Wall of China is visible from space.",
                2,
                false
        );
        tfq2.setId(UUID.randomUUID().toString());

        MultipleChoiceQuestion mcq4 = new MultipleChoiceQuestion(
                "Who wrote 'Romeo and Juliet'?",
                3,
                Arrays.asList(
                        "Charles Dickens",
                        "William Shakespeare",
                        "Jane Austen",
                        "Mark Twain"
                ),
                1
        );
        mcq4.setId(UUID.randomUUID().toString());

        return new Quiz(
                "General Knowledge",
                "Test your general knowledge with these questions",
                Arrays.asList(mcq3, tfq2, mcq4),
                10
        );
    }

    public static void main(String[] args) {
        createQuizzesFile();
    }
}
