package com.example.mastermind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    public GameState() {
    }

    public GameState(String generatedCode, List<Guess> guesses) {
        this.generatedCode = generatedCode;
        this.guesses = guesses;
    }

    private String generatedCode;
    private List<Guess> guesses = new ArrayList<>();

    public void generateCode() {

    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public void setGuesses(List<Guess> guesses) {
        this.guesses = guesses;
    }
}
