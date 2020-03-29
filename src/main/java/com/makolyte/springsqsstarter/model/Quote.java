package com.makolyte.springsqsstarter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Quote {
    @NotNull
    private final String text;
    private final String author;

    @JsonCreator
    public Quote(
            @JsonProperty("text") String text,
            @JsonProperty("author") String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
