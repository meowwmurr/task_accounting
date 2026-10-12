package ru.vsu.cs.mokrushin_k_a.service;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository){
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public void addStudent(String fullName, int groupId){
        if(groupRepository.groupInList(groupId)) {
            Group group = groupRepository.findGroupById(groupId);
            Student student = new Student(fullName, groupId, studentRepository.generateId(), new HashMap<>());
            studentRepository.addStudentToRep(student);
            group.addStudent(student);
            groupRepository.updateGroup(group);
        }
    }

    public void deleteStudent(String fullName, int groupId, int studentId){
        if(groupRepository.groupInList(groupId)){
            Group group = groupRepository.findGroupById(groupId);
            Optional<Student> student = studentRepository.findById(studentId);

            group.deleteStudent(student);
            groupRepository.updateGroup(group);
        }
    }
}
