package storage.core.lib.exceptions.database;

import lombok.Getter;

@Getter
public class DatabaseException extends Exception {
    private final String databaseName;
    private final String message;
    private Exception exception;

    public DatabaseException(String databaseName, String message) {
        this.databaseName = databaseName;
        this.message = message;
    }

    public DatabaseException(String databaseName, String message, Exception exception) {
        this.databaseName = databaseName;
        this.message = message;
        this.exception = exception;
    }
}
