package com.makolyte.springsqsstarter.dto;

import javax.validation.constraints.NotNull;

public class IncomingQuote {

    @NotNull
    private String text;

    private String author;

    public IncomingQuote(@NotNull String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "text='" + text + '\'' +
                ", author='" + author +
                '}';
    }
}
