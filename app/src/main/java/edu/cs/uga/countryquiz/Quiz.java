package edu.cs.uga.countryquiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {
    private long id;
    private String date;
    private List<String> questions;
    private List<String> questionAnswers;
    private int result;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.questions = null;
        this.questionAnswers = null;
        this.result = 0;
    }

    public Quiz(String date, List<String> questions, int result) {
        this.id = -1;
        this.date = date;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        this.result = result;
    }

    public Quiz(String date, List<String> questions, List<String> questionAnswers, int result) {
        this.id = -1;
        this.date = date;
        this.questions = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        for (String question: questions) {
            this.questions.add(question);
        }
        for (String answer: questionAnswers) {
            this.questionAnswers.add(answer);
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
        if (this.questions == null)
            this.questions = new ArrayList<>();

        for (String question: questions) {
            this.questions.add(question);
        }
    }

    public List<String> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<String> questionAnswers) {
        if (this.questionAnswers == null)
            this.questionAnswers = new ArrayList<>();

        for (String answer: questionAnswers) {
            this.questions.add(answer);
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