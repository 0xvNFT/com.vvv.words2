package com.vvv.words2.Models;

import java.util.List;

public class LevelModel {
    List<WordModel> wordModels;
    String name, challenge;
    int gridSize;

    public LevelModel(String name, String challenge, List<WordModel> wordModels, int gridSize) {
        this.name = name;
        this.challenge = challenge;
        this.wordModels = wordModels;
        this.gridSize = gridSize;
    }

    public LevelModel() {
    }

    public List<WordModel> getWords() {
        return wordModels;
    }

    public void setWords(List<WordModel> wordModels) {
        this.wordModels = wordModels;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
