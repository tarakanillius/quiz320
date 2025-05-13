package com.tarakan.repository;

import com.tarakan.exception.QuizException;
import com.tarakan.model.TrueFalseQuestion;
import org.junit.jupiter.api.Test;
import com.tarakan.model.Quiz;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizRepositoryTest {
    @Test
    public void testQuizRepositoryInitialization() {
        QuizRepository quizRepository = new QuizRepository();
        assertNotNull(quizRepository);
    }

    @Test
    public void testSave() {
        QuizRepository quizRepository = new QuizRepository();
        assertThrows(QuizException.class, quizRepository::save);
        assertThrows(QuizException.class, () -> quizRepository.save(null));
    }

    @Test
    public void testFindById() {
        QuizRepository quizRepository = new QuizRepository();

        Quiz quiz1 = new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true)));
        Quiz quiz2 = new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true)));
        quizRepository.save(quiz1,quiz2);

        var result1 = quizRepository.findById(quiz1.getId());
        var result2 = quizRepository.findById(quiz2.getId());

        assertNotNull(result1);
        assertEquals(quiz1.getId(), result1.getId());
        assertEquals(quiz2.getId(), result2.getId());
        assertNotEquals(result1.getId(), result2.getId());
    }

    @Test
    public void testFindAll() {
        QuizRepository quizRepository1 = new QuizRepository();
        QuizRepository quizRepository2 = new QuizRepository();

        Quiz quiz1 = new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true)));
        Quiz quiz2 = new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true)));
        quizRepository1.save(quiz1,quiz2);

        var result = quizRepository1.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(quiz1));
        assertTrue(result.contains(quiz2));
        assertEquals(0,quizRepository2.findAll().size());
    }

    @Test
    public void testDelete() {
        QuizRepository quizRepository = new QuizRepository();

        Quiz quiz1 = new Quiz("Quiz 1", "Description 1", List.of(new TrueFalseQuestion("Text",1,true)));
        quizRepository.save(quiz1);

        assertNotNull(quizRepository.findById(quiz1.getId()));
        assertThrows(QuizException.class, () -> quizRepository.delete(null));
    }

    @Test
    public void testSaveAll(){
        QuizRepository quizRepository = new QuizRepository();
        assertThrows(QuizException.class, () -> quizRepository.saveAll(null));
        assertThrows(QuizException.class, () -> quizRepository.saveAll(List.of()));
    }
}
