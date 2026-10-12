package ru.vsu.cs.mokrushin_k_a.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(int taskId){
        super("Task №" + taskId + "not found");
    }
}
