package com.themaj.smart_books.service;

import com.themaj.smart_books.Statementparser.StatementParser;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StatementService {
    private final ParserFactory parserFactory;
    private final TransactionRepository transactionRepository;

    public StatementService(ParserFactory parserFactory, TransactionRepository transactionRepository) {
        this.parserFactory = parserFactory;
        this.transactionRepository = transactionRepository;
    }

    public void process(MultipartFile file, String bank, String fileType) throws Exception {

        StatementParser statementParser = parserFactory.getParser(bank, fileType);
        //statementParser.parse(file);

        List<Transaction> transactions = statementParser.parse(file);
        System.out.println("transactions " + transactions.size());
        transactionRepository.saveAll(transactions);
    }

}