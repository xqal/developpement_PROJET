package com.example.test.Activity;

public class Question {
    private int id;
    private String Question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String reponse;

    public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD, String reponse) {
        this.id = id;
        this.Question = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {

        return Question;
    }

    public String getOptionA() {

        return optionA;
    }

    public String getOptionB() {

        return optionB;
    }

    public String getOptionC() {

        return optionC;
    }

    public String getOptionD() {

        return optionD;
    }

    public String getReponse() {

        return reponse;
    }
}

