package edu.cs.uga.countryquiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private long id;
    private String date;
    private List<String> questions;
    private int result;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.questions = null;
        this.result = 0;
    }

    public Quiz(String date, List<String> questions, int result) {
        this.id = -1;
        this.date = date;
        this.questions = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        this.result = result;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        for (String question: questions) {
            this.questions.add(question);
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String toString() {
        return id + ": " + date + " " + result;
    }
}
