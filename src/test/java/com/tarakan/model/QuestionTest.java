package com.tarakan.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    @Test
    public void testGetText() {
        TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion("Text", 1, false);
        assertEquals("Text", trueFalseQuestion.getText());
    }

    @Test
    public void testGetPoints() {
        TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion("Text", 1, false);
        assertEquals(1, trueFalseQuestion.getPoints());
    }
}
