package ru.vsu.cs.mokrushin_k_a.exception;

public class InvalidStudentNameException extends RuntimeException {
    public InvalidStudentNameException(String fullName) {
        super("Имя " + fullName + "не валидно");
    }
}
