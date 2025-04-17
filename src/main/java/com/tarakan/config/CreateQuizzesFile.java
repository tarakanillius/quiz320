package com.tarakan.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarakan.service.ObjectIdSerializer;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.util.ArrayList;
import com.tarakan.model.*;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class CreateQuizzesFile {
    private static final String QUIZZES_FILE_PATH = "src/main/resources/quizzes.json";

    public static void createQuizzesFile() {
        List<Quiz> quizzes = createSampleQuizzes();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            SimpleModule module = new SimpleModule();
            module.addSerializer(ObjectId.class, new ObjectIdSerializer());
            objectMapper.registerModule(module);
            File file = new File(QUIZZES_FILE_PATH);
            file.getParentFile().mkdirs();
            objectMapper.writeValue(file, quizzes);
            System.out.println("Successfully created quizzes.json with " + quizzes.size() + " sample quizzes");
        } catch (IOException e) {
            System.err.println("Error creating quizzes.json file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<Quiz> createSampleQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        List<Question> javaQuestions = new ArrayList<>();
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
        mcq1.setId(new ObjectId());
        javaQuestions.add(mcq1);
        TrueFalseQuestion tfq1 = new TrueFalseQuestion(
                "Java is a purely object-oriented language.",
                3,
                false
        );
        tfq1.setId(new ObjectId());
        javaQuestions.add(tfq1);
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
        mcq2.setId(new ObjectId());
        javaQuestions.add(mcq2);
        Quiz javaQuiz = new Quiz(
                "Java Programming Basics",
                "Test your knowledge of Java programming fundamentals",
                javaQuestions,
                15
        );
        javaQuiz.setId(new ObjectId());
        List<Question> generalQuestions = new ArrayList<>();
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
        mcq3.setId(new ObjectId());
        generalQuestions.add(mcq3);
        TrueFalseQuestion tfq2 = new TrueFalseQuestion(
                "The Great Wall of China is visible from space.",
                2,
                false
        );
        tfq2.setId(new ObjectId());
        generalQuestions.add(tfq2);
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
        mcq4.setId(new ObjectId());
        generalQuestions.add(mcq4);
        Quiz generalQuiz = new Quiz(
                "General Knowledge",
                "Test your general knowledge with these questions",
                generalQuestions,
                10
        );
        generalQuiz.setId(new ObjectId());
        quizzes.add(javaQuiz);
        quizzes.add(generalQuiz);
        return quizzes;
    }

    public static void main(String[] args) {
        createQuizzesFile();
    }
}
