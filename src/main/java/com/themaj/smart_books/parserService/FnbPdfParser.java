package com.themaj.smart_books.service;

import com.themaj.smart_books.Statementparser.StatementParser;

import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.model.TransactionType;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Component
public class FnbPdfParser implements StatementParser {

    private static final Pattern TRANSACTION_PATTERN = Pattern.compile(
            "(\\d{2}\\s+[A-Za-z){3})\\s+(.*?)\\s+(\\d+[.,]\\d{2})(Cr|Dr)?\\s+([\\d,]+\\.\\d{2})])");


    @Override
    public List<Transaction> parse(MultipartFile file) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        try (PDDocument pdDocument = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper textStripper = new PDFTextStripper();
            String text =  textStripper.getText(pdDocument);
            System.out.println(text);
            boolean readTransactions = false;
            String[] lines = text.split("\\r?\\n");
            for (String line : lines) {
                line = line.trim();
                System.out.println("LINE ->" + line);
                if (line.startsWith("Date")) {
                    continue;
                }
                if (line.contains("Transactions in RAND")) {
                    System.out.println("FOUND TRANSACTION SECTION");
                    readTransactions = true;
                    continue;
                }
                if (!readTransactions) {
                    continue;
                }
                //if (isTransactionLine(line)) {
                if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                    System.out.println("Transaction detected " + line);
                    Transaction transact = parseTransaction(line);
                    if (transact != null) {
                        transactions.add(transact);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error passing FNB PDF", e);
        }
        return transactions;
    }
    private Transaction parseTransaction(String line) {

        line = line.trim();

        // Only process lines starting with a date like "12 Jan"
        if (!line.matches("^\\d{2}\\s+[A-Za-z]{3}.*")) {
            return null;
        }

        String[] parts = line.split("\\s+");

        // Minimum structure check
        if (parts.length < 4) {
            System.out.println("Skipping short line: " + line);
            return null;
        }

        try {

            // Date
            String dateStr = parts[0] + " " + parts[1];

            // Amount and balance are always at the end
            String amountStr = parts[parts.length - 2];
            String balanceStr = parts[parts.length - 1];

            // Everything between date and amount is description
            StringBuilder descriptionBuilder = new StringBuilder();
            for (int i = 2; i < parts.length - 2; i++) {
                descriptionBuilder.append(parts[i]).append(" ");
            }

            String description = descriptionBuilder.toString().trim();
            if (description == null || description.isEmpty()) {
                System.out.println("Unknown transaction " + line);
                return  null;
            }
            Transaction transaction = getTransaction(amountStr, balanceStr, description, dateStr);

            System.out.println("Parsed transaction: " + description);

            return transaction;

        } catch (Exception e) {
            System.out.println("Failed parsing line: " + line);
            return null;
        }
    }

    private static Transaction getTransaction(String amountStr, String balanceStr, String description, String dateStr) {
        BigDecimal amount = new BigDecimal(amountStr.replace(",", ""));
        BigDecimal balance = new BigDecimal(balanceStr.replace(",", ""));


        TransactionType type = amount.compareTo(BigDecimal.ZERO) < 0
                ? TransactionType.EXPENSE
                : TransactionType.INCOME;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse(dateStr + " 2026", formatter);

        Transaction transaction = new Transaction();
        transaction.setDate(date);
        transaction.setDescription(description);
        transaction.setAmountExclVat(amount.abs());
        transaction.setOpeningBalance(balance);
        transaction.setType(type);
        return transaction;
    }

    @Override
    public String getBankName() {
        return "FNB";
    }

    @Override
    public String getFileType() {
        return "PDF";
    }
}
