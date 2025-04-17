package com.tarakan.model;

import org.bson.types.ObjectId;

public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion() {}

    public TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(Object response) {
        if (!(response instanceof Boolean)) {
            return false;
        }
        return ((Boolean) response) == correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    @Override
    public String toString() {
        return "TrueFalseQuestion{" +
                "correctAnswer=" + correctAnswer +
                "} " + super.toString();
    }
}
