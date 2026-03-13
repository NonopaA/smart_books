package com.themaj.smart_books.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    private Long id;
    private String text;
    private LocalDateTime createdAt;
    @ManyToOne
    private Transaction transaction;

    public Comment() {
    }

    public Comment(String text, LocalDateTime createdAt, Transaction transaction) {
        this.text = text;
        this.createdAt = createdAt;
        this.transaction = transaction;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", transaction=" + transaction +
                '}';
    }
}
