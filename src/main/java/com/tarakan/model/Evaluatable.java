package com.tarakan.model;

public interface Evaluatable {
    boolean evaluate(Object response);
    int getPoints();
}