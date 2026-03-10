package com.themaj.smart_books.service;

import com.themaj.smart_books.Statementparser.StatementParser;
import com.themaj.smart_books.model.ParserType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParserFactory {
    private final Map<String, StatementParser> parserMap = new HashMap<>();
    public ParserFactory(List<StatementParser> parsers) {
        for (StatementParser parser : parsers) {
            String key = parser.getBankName().trim().toUpperCase() + "_" + parser
                    .getFileType().trim().toUpperCase();
            System.out.println("registering parser key: " + key);
            System.out.println(parser.getClass().getSimpleName() );
            System.out.println("bank  " + parser.getBankName());
            parserMap.put(key, parser);
        }
    }
    public  StatementParser getParser(String bank, String fileType) {
        if (bank == null || bank.trim().isEmpty()) {
            throw new RuntimeException("Bank must be provided");
        }
        String key = bank.trim().toUpperCase() + "_" + fileType.trim().toUpperCase();
        System.out.println("looking for key: " +  key + " ");
        StatementParser parser = parserMap.get(key);
        if (parser == null) throw new RuntimeException("Unsupported bank: " + key);
        return parser;
    }
}

