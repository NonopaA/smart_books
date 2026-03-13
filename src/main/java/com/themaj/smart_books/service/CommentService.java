package com.themaj.smart_books.service;

import com.themaj.smart_books.model.Comment;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.repository.CommentRepository;
import com.themaj.smart_books.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TransactionRepository transactionRepository;

    public CommentService(CommentRepository commentRepository, TransactionRepository transactionRepository) {
        this.commentRepository = commentRepository;
        this.transactionRepository = transactionRepository;
    }
    public Comment createComment(Long transactionId, String text) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setTransaction(transaction);
        Instant LocalDateTime = null;
        comment.setCreatedAt(java.time.LocalDateTime.from((LocalDateTime).now()));

        return commentRepository.save(comment);
    }
}
