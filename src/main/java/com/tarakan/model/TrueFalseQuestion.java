package com.tarakan.model;

public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion() {}

    public TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(Object response) {
        if (!(response instanceof Boolean)) return false;
        return ((Boolean) response) == correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "TrueFalseQuestion{" +
                "correctAnswer=" + correctAnswer +
                "} " + super.toString();
    }
}
