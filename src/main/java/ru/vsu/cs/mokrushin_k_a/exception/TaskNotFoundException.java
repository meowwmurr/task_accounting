package ru.vsu.cs.mokrushin_k_a.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(int taskId){
        super("Задача с номером" + taskId + "не найдена");
    }
}
