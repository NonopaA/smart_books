" id="41826"}
SmartBooks is a backend financial transaction processing system built with Spring Boot.

The application imports bank statements and automatically parses and categorizes transactions.

## Features

- Upload bank statements (CSV / PDF)
- Parse statements from multiple banks
- Automatic transaction categorization
- Category rule engine
- Transaction storage and retrieval
- REST API for managing transactions and categories

## Supported Banks

- Capitec
- FNB
- Nedbank
- Standard Bank

## Architecture

The project follows a layered Spring Boot architecture:

Controller Layer  
Handles API requests and responses.

Service Layer  
Contains the business logic.

Repository Layer  
Handles database access using Spring Data JPA.

Parser Layer  
Responsible for parsing different bank statement formats.

## Technologies

- Java
- Spring Boot
- Maven
- JPA / Hibernate
- REST APIs

## Project Structure

src/main/java/com/themaj/smart_books

- controller
- service
- repository
- model
- parserService

## Author

A. Nonopa
