FROM openjdk:22-jdk
LABEL authors="A.NONOPA"
WORKDIR /smart_books
COPY target/smart_books.jar smart_books.jar

ENTRYPOINT ["java", "-jar", "smart_books.jar"]