package ru.vsu.cs.mokrushin_k_a.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
