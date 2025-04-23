package com.tarakan.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MultipleChoiceQuestion"),
        @JsonSubTypes.Type(value = TrueFalseQuestion.class, name = "TrueFalseQuestion")
})

public abstract class Question implements QuestionEvaluator {
    protected String text;
    protected int points;

    public Question() {}

    public Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
