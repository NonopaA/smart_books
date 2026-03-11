package com.themaj.smart_books;

import com.themaj.smart_books.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRulesTest {
    @Autowired
    private TransactionService transactionService;
}
