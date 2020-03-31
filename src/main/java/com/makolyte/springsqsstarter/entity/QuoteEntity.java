package com.makolyte.springsqsstarter.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String text;

    private String author;

    @NotNull
    private String awsMessageId;

    private Instant dateReceived;

    //required by Hibernate
    public QuoteEntity() {
    }

    public QuoteEntity(
            @NotNull String text,
            String author,
            @NotNull String awsMessageId,
            Instant dateReceived) {
        this.text = text;
        this.author = author;
        this.awsMessageId = awsMessageId;
        this.dateReceived = dateReceived;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAwsMessageId() {
        return awsMessageId;
    }

    public void setAwsMessageId(String awsMessageId) {
        this.awsMessageId = awsMessageId;
    }

    public Instant getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Instant dateReceived) {
        this.dateReceived = dateReceived;
    }
}
