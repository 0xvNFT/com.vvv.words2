package com.vvv.words2.Models;

public class WordModel {

    private String word;
    //    @PropertyName("isHorizontal")
    private boolean horizontal;
    private int fromRow, fromColumn, toRow, toColumn;

    public WordModel(String word, int fromRow, int fromColumn, int toRow, int toColumn, boolean horizontal) {
        this.word = word;
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
        this.horizontal = horizontal;
    }

    public WordModel() {
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getFromRow() {
        return fromRow;
    }

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    public int getFromColumn() {
        return fromColumn;
    }

    public void setFromColumn(int fromColumn) {
        this.fromColumn = fromColumn;
    }

    public int getToRow() {
        return toRow;
    }

    public void setToRow(int toRow) {
        this.toRow = toRow;
    }

    public int getToColumn() {
        return toColumn;
    }

    public void setToColumn(int toColumn) {
        this.toColumn = toColumn;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
