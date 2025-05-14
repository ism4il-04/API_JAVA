package TP9.db;

public class ConnectException extends Exception {
    public ConnectException(String message, Throwable e) {
        super(message, e);
    }
}
