package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class CardDTO {
    @NotEmpty
    private String question;
    @NotEmpty
    private String answer;
    @NotEmpty
    private String tags;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
