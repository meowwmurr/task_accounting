package ru.university.mokrushin_k_a.model;

import java.util.Map;

public class Student {
    private final int groupId;
    private int studentId;
    private final String full_name;
    private Map<Integer, Boolean> tasks;

    public Student(String full_name, int groupId){
        this.full_name = full_name;
        this.groupId = groupId;
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
        return tasks;
    }

    public void setTasks(Map<Integer, Boolean> new_tasks){
        tasks = new_tasks;
    }
}
