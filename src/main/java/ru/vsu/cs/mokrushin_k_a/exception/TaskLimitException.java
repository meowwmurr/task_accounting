package ru.vsu.cs.mokrushin_k_a.exception;

public class TaskLimitException extends RuntimeException {
    public TaskLimitException() {
        super("Limit tasks = 3, just chill ))");
    }
}
