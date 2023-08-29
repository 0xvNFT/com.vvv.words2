package com.vvv.words2.Models;

import android.graphics.Rect;

public class CellModel {
    private Rect rect;
    private char letter;
    private int rowIndex, columnIndex;
    private boolean isPartOfWord;

    public CellModel(Rect rect, char letter, int rowIndex, int columnIndex) {
        this.rect = rect;
        this.letter = letter;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public boolean isPartOfWord() {
        return isPartOfWord;
    }

    public void setPartOfWord(boolean partOfWord) {
        isPartOfWord = partOfWord;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getRow() {
        return rowIndex;
    }

    public void setRow(int row) {
        this.rowIndex = row;
    }

    public int getColumn() {
        return columnIndex;
    }

    public void setColumn(int column) {
        this.columnIndex = column;
    }
}