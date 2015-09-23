package com.sebprunier.nantes_quizz.photos;

public class PhotoQuizzResult {

    private boolean result;

    private String correctOption;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
}
