package com.themaj.smart_books.parser;

import com.themaj.smart_books.statementparser.StatementParser;

import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.model.TransactionType;
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
import java.util.regex.Pattern;



@Component
public class FnbPdfParser implements StatementParser {

    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public List<Transaction> parse(MultipartFile file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        //PDDocument represents the actual PDF file
        try (PDDocument pdDocument = Loader.loadPDF(file.getBytes())) {

            //This class extracts text from PDF.
            PDFTextStripper textStripper = new PDFTextStripper();

            //extracts all text from the pdf document
            String text =  textStripper.getText(pdDocument);

            //break the big text into lines
            String[] lines = text.split("\n");

            //Loop through all lines extracted from pdf
            for (String line : lines) {

                //does is it look like a transaction?
                if (isTransactionLine(line)) {
                    Transaction transact = parseTransaction(line);
                    transactions.add(transact);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error passing FNB PDF", e);
        }
        return transactions;
    }

    private Transaction parseTransaction(String line) {
        /*splitting per line: 01/05/2025 Checkers -500.00 1200.00
        parts[0] = "01/05/2025"
        parts[1] = "Checkers"
        parts[2] = "-500.00"
        parts[3] = "1200.00"
        */
        String[] parts = line.split(" ");

        //extract date
        LocalDate date = LocalDate.parse(parts[0], DATE_FORMAT);

        //extract description
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

    //Line starts with a date, if it doesn't ignore it.
    private boolean isTransactionLine(String line) {
        return line.matches("\\d{2}/\\d{2}/\\d{4}.*");
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
