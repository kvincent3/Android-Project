package com.example.englishproject;

import java.util.ArrayList;

public class Question {

    private int id;
    private Boolean passed;
    private String question;
    private String correct;
    private String place;
    private ArrayList<String> choices = new ArrayList<String>();
    private ArrayList<String> hints = new ArrayList<String>();

    public Question() {
        super();
        this.passed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public void addChoice(String a){
        this.choices.add(a);
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setchoices(ArrayList<String> answers) {
        this.choices = answers;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public void setHints(ArrayList<String> hints) {
        this.hints = hints;
    }

    public void addHint(String hint) {
        this.hints.add(hint);
    }


    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getCorrect() {
        return correct;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", passed=" + passed +
                ", question='" + question + '\'' +
                ", correct='" + correct + '\'' +
                ", place='" + place + '\'' +
                ", choices=" + choices +
                ", hints=" + hints +
                '}';
    }
}


