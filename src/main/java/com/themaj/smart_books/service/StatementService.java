package com.themaj.smart_books.service;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.statementparser.StatementParser;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/*
* Flow:
*Receive uploaded file from UploadController
*Select the correct parser using ParserFactory
*Parse CSV/PDF into Transaction objects
* Save transactions into a database
* Categorize transactions if rules exist
 */

@Service
public class StatementService {
    private final ParserFactory parserFactory;
    private final TransactionRepository transactionRepository;

    public StatementService(ParserFactory parserFactory, TransactionRepository transactionRepository) {
        this.parserFactory = parserFactory;
        this.transactionRepository = transactionRepository;
    }

    public void process(MultipartFile file, String bank, String fileType) throws Exception {
    //Select the correct parser based on bank and file type
        StatementParser statementParser = parserFactory.getParser(bank, fileType);

        //Convert uploaded statement into Transaction objects
        List<Transaction> transactions = statementParser.parse(file); //read file
        System.out.println("transactions " + transactions.size());
        transactionRepository.saveAll(transactions);
    }
public TransactionSummaryDto getSummary() {
    BigDecimal income = Optional.ofNullable(transactionRepository.sumIncome())
            .orElse(BigDecimal.ZERO);
    BigDecimal expenses = Optional.ofNullable(transactionRepository.sumExpenses())
            .orElse(BigDecimal.ZERO);

    BigDecimal balance = income.subtract(expenses);
    return new TransactionSummaryDto(income, expenses, balance);
}

public Transaction save(Transaction transaction) {
        transaction.setType(transaction.getCategory().getType());


    return transactionRepository.save(transaction);
}
public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
}

}