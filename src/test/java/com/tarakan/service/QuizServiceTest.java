package com.tarakan.service;

import com.tarakan.repository.QuizRepository;
import com.tarakan.exception.QuizException;
import com.tarakan.model.TrueFalseQuestion;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import com.tarakan.model.Quiz;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizServiceTest {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String QUIZZES_FILE_PATH = dotenv.get("QUIZZES_FILE_PATH");

    @Test
    public void testQuizServiceInitialization() {
        assertThrows(IllegalArgumentException.class, () -> new QuizService(new QuizRepository(), ""));
    }

    @Test
    public void testCreateQuiz() {
        QuizService quizService = new QuizService(new QuizRepository(), QUIZZES_FILE_PATH);
        assertThrows(QuizException.class , () -> quizService.createQuiz(new Quiz(null, null, null)));
    }

    @Test
    public void testGetAllQuizzes() {
        QuizRepository quizRepository = new QuizRepository();
        QuizService quizService = new QuizService(quizRepository, "blank");
        quizRepository.save(new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true))));
        assertEquals(1, quizService.getAllQuizzes().size());
    }

    @Test
    public void testDeleteQuiz() {
        QuizService quizService = new QuizService(new QuizRepository(), "blank");
        assertThrows(QuizException.class, () -> quizService.deleteQuiz(null));
    }
}
