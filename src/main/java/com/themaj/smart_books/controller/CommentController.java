package com.themaj.smart_books.controller;

import com.themaj.smart_books.model.Comment;
import com.themaj.smart_books.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestParam Long transactionId, @RequestParam String text) {
        return commentService.createComment(transactionId, text);
    }
}
