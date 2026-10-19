package ru.vsu.cs.mokrushin_k_a.service;

import ru.vsu.cs.mokrushin_k_a.exception.GroupNotFoundException;
import ru.vsu.cs.mokrushin_k_a.exception.StudentNotFoundException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository){
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public void addStudent(String fullName, int groupId){
        Group group = groupRepository.findGroupById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        Student student = new Student(fullName, groupId, studentRepository.generateId(), new HashMap<>());

        studentRepository.addStudentToRep(student);
        group.addStudent(student);
        groupRepository.updateGroup(group);
    }

    public void deleteStudent(int studentId){
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException(String.valueOf(studentId)));
            Group group = groupRepository.findGroupById(student.getGroupId()).orElseThrow(() -> new IllegalArgumentException(String.valueOf(student.getGroupId())));

            group.deleteStudent(student);
            studentRepository.deleteStudentFromRepo(student);
            groupRepository.updateGroup(group);
    }

    public void updateTaskStatus(int studentId, int taskNum, boolean status){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        student.updateTasks(taskNum, status);
        studentRepository.updateStudent(student);
    }

    public Optional<Student> getStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    public List<Student> getStudentsByGroup(int groupId) {
        Group group = groupRepository.findGroupById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        return group.getStudentsList();
    }
}
