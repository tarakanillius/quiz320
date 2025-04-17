package com.tarakan.model;

import org.bson.types.ObjectId;

import java.util.List;

public class MultipleChoiceQuestion extends Question implements Evaluatable {
    private List<String> options;
    private int correctOptionIndex;

    public MultipleChoiceQuestion() {}

    public MultipleChoiceQuestion(String text, int points, List<String> options, int correctOptionIndex) {
        super(text, points);
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    @Override
    public boolean evaluate(Object response) {
        if (!(response instanceof Integer)) {
            return false;
        }
        return ((Integer) response) == correctOptionIndex;
    }

    @Override
    public int getPoints() {
        return points;
    }

}
