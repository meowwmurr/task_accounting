package ru.vsu.cs.mokrushin_k_a.model;

import ru.vsu.cs.mokrushin_k_a.exception.InvalidStudentNameException;
import ru.vsu.cs.mokrushin_k_a.exception.TaskLimitException;
import ru.vsu.cs.mokrushin_k_a.exception.TaskNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private final int groupId;
    private final int studentId;
    private final String fullName;
    private Map<Integer, Boolean> tasks;

    public Student(String fullName, int groupId, int studentId, Map<Integer, Boolean> tasks){
        if (fullName == null || fullName.isBlank()) {
            throw new InvalidStudentNameException(fullName);
        }
        this.fullName = fullName;
        this.groupId = groupId;
        this.studentId = studentId;
        this.tasks = new HashMap<>(tasks);
    }

    public String getFullName() {
        return fullName;
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

    public void updateTasks(int taskNum, boolean status){
        if (taskNum < 1 || taskNum > 3){
            throw new TaskNotFoundException(taskNum);
        }
        if (tasks.containsKey(taskNum)){
            tasks.put(taskNum, status);
        }else{
        if (tasks.size() >= 3){throw new TaskLimitException();}
            tasks.put(taskNum, status);
        }
    }

    public boolean taskStatus(int taskNum){
        if (taskNum < 1 || taskNum > 3){throw new TaskNotFoundException(taskNum);}
        return tasks.getOrDefault(taskNum, false);
    }

    @Override
    public boolean equals(Object o){
        if (o == this){return true;}
        if (o == null || this.getClass() != o.getClass()) {return false;}

        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id = " + studentId +
                ", name = '" + fullName + '\'' +
                ", group = " + groupId +
                ", tasks = " + tasks +
                '}';
    }
}
