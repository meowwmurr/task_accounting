package ru.vsu.cs.mokrushin_k_a.exception;

public class TaskLimitException extends RuntimeException {
    public TaskLimitException() {
        super("Лимит тасков(3) превышен, отдыхайте");
    }
}
