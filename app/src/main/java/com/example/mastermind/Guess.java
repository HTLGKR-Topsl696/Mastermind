package com.example.mastermind;

import java.io.Serializable;

public class Guess implements Serializable {
    private String guess;
    private String result;

    public Guess() {
    }

    public Guess(String guess, String result) {
        this.guess = guess;
        this.result = result;
    }

    public String getGuess() {
        return guess;
    }

    public String getResult() {
        return result;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String toString() {
        return guess + " | " + result;
    }
}
