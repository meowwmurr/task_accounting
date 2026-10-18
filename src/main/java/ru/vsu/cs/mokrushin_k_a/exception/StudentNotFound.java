package ru.vsu.cs.mokrushin_k_a.exception;

public class StudentNotFound extends BusinessException{
    public StudentNotFound(int studentId) {
        super("Student not found with id = " + studentId);
    }
}
