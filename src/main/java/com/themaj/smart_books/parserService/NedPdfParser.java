package com.themaj.smart_books.service;

import com.themaj.smart_books.Statementparser.StatementParser;
import com.themaj.smart_books.model.Transaction;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component
public class NedPdfParser implements StatementParser {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public List<Transaction> parse(MultipartFile file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (PDDocument pdDocument = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper textStripper = new PDFTextStripper();
            String text =  textStripper.getText(pdDocument);
            String[] lines = text.split("\n");
            for (String line : lines) {
                if (isTransactionLine(line)) {
                    Transaction transact = parseTransaction(line);
                    transactions.add(transact);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error passing NEDBANK PDF", e);
        }
        return transactions;
    }

    private Transaction parseTransaction(String line) {
        String[] parts = line.split(" ");
        LocalDate date = LocalDate.parse(parts[0], DATE_FORMAT);
        String description = parts[0];
        BigDecimal amountExclVat = new BigDecimal(parts[2]);
        BigDecimal vat = new BigDecimal(parts[3]);
        BigDecimal revenue = new BigDecimal(parts[4]);
        BigDecimal openingBalance = new BigDecimal(parts[5]);
        BigDecimal total = new BigDecimal(parts[6]);
        String comment = parts[7];
        Transaction transact = new Transaction();
        transact.setDate(date);
        transact.setDescription(description);
        transact.setAmountExclVat(amountExclVat);
        transact.setVat(vat);
        transact.setRevenue(revenue);
        transact.setOpeningBalance(openingBalance);
        transact.setTotal(total);
        transact.setComment(comment);
        return transact;
    }

    private boolean isTransactionLine(String line) {
        return line.matches("\\d{2}/\\d{2}/\\d{4}.*");
    }

    @Override
    public String getBankName() {
        return "NED";
    }

    public String getFileType() { return "PDF";}
}
