package ru.vsu.cs.mokrushin_k_a.model;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private int groupId;
    private final int courseNumber;
    private String groupName;
    private List<Student> studentsList = new ArrayList<>();

    public Group(int courseNumber, int groupId, String groupName){
        this.courseNumber = courseNumber;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId(){
        return groupId;
    }

    public int setGroupId(){
        return groupId;
    }

    public void addStudent(Student student){
        if (student == null){
            throw new GroupException("Студент не может быть null");
        }
        else{
            if (studentsList.contains(student)){
                throw new GroupException("Студент уже есть в списке");
            } else if (student.getGroupId() != this.groupId) {
                throw new GroupException("Студент не с той группы");
            }
            studentsList.add(student);
        }
    }

    public void deleteStudent(Student student){
        studentsList.remove(student);
    }

    public List<Student> getStudentsList(){
        return Collections.unmodifiableList(studentsList);
    }

    public String getGroupName() {
        return groupName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
}
