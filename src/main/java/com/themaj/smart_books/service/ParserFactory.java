package com.themaj.smart_books.service;

import com.themaj.smart_books.statementparser.StatementParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* PURPOSE:
* Choose the correct parser based on bank and file type
* eg. FNB + PDF -> FnbPdfParser
 ABSA + CSV -> AbsaCsvParser
* 1. Spring automatically finds all classes implementing StatementParser and passes them into the
constructor as a List<StatementParser>
2. The constructor loops through each parser.
3. A unique key is built for every parser:
*     BANK-FILETYPE
*   Example:
*   FNB-PDF
*   ABSA-CSV
4. Each parser is stored in parserMap.

    Example:
    FNB + PDF -> FnbPdfParser
    ABSA + CSV -> AbsaCsvParser
5. Later, getParser(bank, fileType) builds the same key from user input and searches the map.
*6. The correct parser object is returned.
WHY THIS IS GOOD DESIGN:
-avoids many if/else statements
-easy to extend
-adding a new parser requires no changes to the ParserFactory
-clean and scalable
* trim() and toUpperCae() normalize input so "fnb" "FnB" all become FNB
*
* */
@Component
public class ParserFactory {
    private final Map<String, StatementParser> parserMap = new HashMap<>();
    //This constructor initializes the parser map
    public ParserFactory(List<StatementParser> parsers) {
        for (StatementParser parser : parsers) {
            //build a key
            String key = parser.getBankName().trim().toUpperCase() + "_" + parser
                    .getFileType().trim().toUpperCase();
            parserMap.put(key, parser);
        }
    }
    public  StatementParser getParser(String bank, String fileType) {
        if (bank == null || bank.trim().isEmpty()) {
            throw new RuntimeException("Bank must be provided");
        }
        String key = bank.trim().toUpperCase() + "_" + fileType.trim().toUpperCase();
        StatementParser parser = parserMap.get(key);
        if (parser == null) throw new RuntimeException("Unsupported bank: " + key);
        return parser;
    }
}

