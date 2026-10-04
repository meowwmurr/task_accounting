package ru.vsu.cs.mokrushin_k_a.model;

import ru.vsu.cs.mokrushin_k_a.exception.TaskLimitException;
import ru.vsu.cs.mokrushin_k_a.exception.TaskNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private int groupId;
    private int studentId;
    private final String full_name;
    private Map<Integer, Boolean> tasks;

    public Student(String full_name, int groupId){
        this.full_name = full_name;
        this.groupId = groupId;
        this.tasks = new HashMap<>();
    }

    public Student(String full_name, int groupId, int studentId, Map<Integer, Boolean> tasks){
        this.full_name = full_name;
        this.groupId = groupId;
        this.studentId = studentId;
        this.tasks = tasks;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Map<Integer, Boolean> getTasks() {
        return Collections.unmodifiableMap(tasks);
    }

    public void setGroupId(int groupId){
        this.groupId = groupId;
    }

    public void updateTasks(int taskNum, boolean status){
        if (taskNum < 1 || taskNum > 3){
            throw new TaskNotFoundException(taskNum);
        }
        if (tasks.containsKey(taskNum)){
            tasks.put(taskNum, status);
        }
        if (tasks.size() > 3){throw new TaskLimitException("Лимит тасков(3) превышен, отдыхайте");}
        else {
            tasks.put(taskNum, status);
        }
    }

    public boolean taskStatus(int taskNum){return tasks.getOrDefault(taskNum, false);}

    @Override
    public boolean equals(Object o){
        if (o == this){return true;}
        if (o == null || this.getClass() != o.getClass()) {return false;}

        Student student = (Student) o;
        return studentId == student.studentId;
    }
}
