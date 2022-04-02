package com.example.myapplication;

public class CropString {

    private int index;
    private String text;

    public CropString(int index, String text) {
        this.index = index;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public String calculate() {
        if (this.index > this.text.length()) {
            throw new IllegalArgumentException("The number should be smaller than the input string.");
        }
        String result = this.text.substring(0,this.index);
        return result;
    }
}
