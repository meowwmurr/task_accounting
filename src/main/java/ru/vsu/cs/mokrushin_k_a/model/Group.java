package ru.vsu.cs.mokrushin_k_a.model;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private final int groupId;
    private final int courseNumber;
    private final String groupName;
    private List<Student> studentsList = new ArrayList<>();

    public Group(int courseNumber, int groupId, String groupName){
        this.courseNumber = courseNumber;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId(){
        return groupId;
    }

    public void addStudent(Student student){
        if (student == null){
            throw new GroupException("Student cant be null");
        }
        else{
            if (studentsList.contains(student)){
                throw new GroupException("Student is already on this list");
            } else if (student.getGroupId() != this.groupId) {
                throw new GroupException("Student from another group");
            }
            studentsList.add(student);
        }
    }

    public void deleteStudent(Student student) {
        if (student == null) {
            throw new GroupException("Student cant be null");
        }
        if (student.getGroupId() != this.groupId) {
                throw new GroupException("Student from another group");
            }
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

    public int getStudentsCount(){
        return studentsList.size();
    }

    public boolean isEmpty(){
        return studentsList.isEmpty();
    }

    @Override
    public boolean equals(Object o){
        if (o == this){return true;}
        if (o == null || this.getClass() != o.getClass()) {return false;}

        Group group = (Group) o;
        return groupId == group.groupId;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(groupId);
    }

    @Override
    public String toString(){
        return "Group " +
                "{id = " + groupId +
                ", name = " + groupName +
                ", course = " + courseNumber +
                ", studentsCount = " + getStudentsCount() +
                "}";
    }
}
