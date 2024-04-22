package com.qppd.rizalelearning;

public class Trivia {

    private String title;
    private String trivia;

    public Trivia(String title, String trivia) {
        this.title = title;
        this.trivia = trivia;
    }

    public Trivia(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrivia() {
        return trivia;
    }

    public void setTrivia(String trivia) {
        this.trivia = trivia;
    }
}
