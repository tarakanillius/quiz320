package com.tarakan.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiceQuestionTest {
    @Test
    public void testMultipleChoiceQuestionInitialization() {
        assertThrows(IllegalArgumentException.class, () -> new MultipleChoiceQuestion(null, 0, null, 0));
    }

    @Test
    public void testMultipleChoiceQuestionGetOptions() {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Text", 1, List.of(), 0);
        assertEquals(List.of(), multipleChoiceQuestion.getOptions());
    }

    @Test
    public void testMultipleChoiceQuestionGetCorrectOptionIndex() {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Text", 1, List.of(), 0);
        assertEquals(0, multipleChoiceQuestion.getCorrectOptionIndex());
    }

    @Test
    public void testMultipleChoiceQuestionEvaluate() {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Text", 1, List.of(), 0);
        assertTrue(multipleChoiceQuestion.evaluate(0));
    }
}
