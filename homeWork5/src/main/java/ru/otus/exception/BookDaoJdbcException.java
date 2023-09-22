package ru.otus.exception;


public class BookDaoJdbcException extends RuntimeException {
    public BookDaoJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
