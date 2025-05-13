package com.tarakan.model;

import com.tarakan.exception.QuizException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrueFalseQuestionTest {
    @Test
    public void testTrueFalseQuestionInitialization() {
        assertThrows(IllegalArgumentException.class, () -> new TrueFalseQuestion(null,0,false));
    }

    @Test
    public void testIsCorrectAnswer() {
        TrueFalseQuestion question1 = new TrueFalseQuestion("1", 1, true);
        TrueFalseQuestion question2 = new TrueFalseQuestion("5", 5, false);
        assertTrue(question1.isCorrectAnswer());
        assertFalse(question2.isCorrectAnswer());
    }

    @Test
    public void testEvaluate(){
        TrueFalseQuestion question1 = new TrueFalseQuestion("1", 1, true);
        assertThrows(QuizException.class, () -> question1.evaluate(null));
    }
}
