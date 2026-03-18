package com.themaj.smart_books.controller;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService statementService;

    public TransactionController(TransactionService transactionService) {
        this.statementService = transactionService;
    }

    @PostMapping
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return statementService.save(transaction);
    }
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return statementService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return statementService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransactionById(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return statementService.save(transaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        statementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/transactions/{transactionId}/category/{categoryId}")
    public void updateCategory(@PathVariable Long transactionId,
                               @PathVariable Long categoryId) {
        statementService.categorizeTransaction(transactionId, categoryId);
    }

}
