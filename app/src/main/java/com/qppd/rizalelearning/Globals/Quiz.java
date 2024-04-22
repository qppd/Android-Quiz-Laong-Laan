package com.qppd.rizalelearning.Globals;

public class Quiz {

    private static String answers[];

    private static int score;

    public static String[] getAnswers() {
        return answers;
    }

    public static void setAnswers(String[] answers) {
        Quiz.answers = answers;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Quiz.score = score;
    }
}
