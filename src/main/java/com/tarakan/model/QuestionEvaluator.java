package com.tarakan.model;

public interface QuestionEvaluator {
    boolean evaluate(Object response);
    int getPoints();
}