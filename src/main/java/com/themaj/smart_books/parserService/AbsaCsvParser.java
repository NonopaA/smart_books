package com.themaj.smart_books.parserService;

import com.themaj.smart_books.Statementparser.StatementParser;
import com.themaj.smart_books.model.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AbsaCsvParser implements StatementParser {
    @Override
    public List<Transaction> parse(MultipartFile file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        reader.readLine();
        String line;
        while((line = reader.readLine()) != null) {
            String[] column = line.split("");
            Transaction transaction = new Transaction();
            transaction.setDate(LocalDate.parse(column[0]));
            transaction.setDescription(column[1]);
            transaction.setAmountExclVat(new BigDecimal(column[2]));
            transaction.setVat(new BigDecimal(column[3]));
            transaction.setOpeningBalance(new BigDecimal(column[4]));
            transaction.setComment((column[5]));
            transaction.setTotal(new BigDecimal(column[6]));
            transaction.setRevenue(new BigDecimal(column[7]));
            transaction.setComment(column[8]);
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public String getBankName() {
        return "ABSA";
    }

    @Override
    public String getFileType() {
        return "CSV";
    }
}
