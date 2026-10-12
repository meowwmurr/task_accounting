package ru.vsu.cs.mokrushin_k_a.exception;

public class InvalidStudentNameException extends RuntimeException {
    public InvalidStudentNameException(Object o) {
        super("Name " + (String)o + "invalid");
    }
    public InvalidStudentNameException(String message){super(message);}
}
