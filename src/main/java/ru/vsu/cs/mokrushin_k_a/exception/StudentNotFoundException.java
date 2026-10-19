package ru.vsu.cs.mokrushin_k_a.exception;

public class StudentNotFoundException extends BusinessException{
    public StudentNotFoundException(int studentId) {
        super("Student not found with id = " + studentId);
    }
}
