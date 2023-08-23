package storage.core.lib.exceptions.database;

import lombok.Getter;

@Getter
public class DatabaseException extends Exception {
    private Exception exceptionFromDatabase;
    private final String message;

    public DatabaseException(String message) {
        this.message = message;
    }

    public DatabaseException(String message, Exception exception) {
        this.message = message;
        this.exceptionFromDatabase = exception;
    }
}
