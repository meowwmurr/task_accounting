package ru.vsu.cs.mokrushin_k_a.service;

import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;

import java.util.HashMap;

public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository){
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public void addStudent(String fullName, int groupId){
        if(groupRepository.groupInList(groupId)) {
            Group group = groupRepository.findGroupById(groupId).orElseThrow(() -> new IllegalArgumentException(String.valueOf(groupId)));
            Student student = new Student(fullName, groupId, studentRepository.generateId(), new HashMap<>());
            studentRepository.addStudentToRep(student);
            group.addStudent(student);
            groupRepository.updateGroup(group);
        }
    }

    public void deleteStudent(int studentId){
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException(String.valueOf(studentId)));
            Group group = groupRepository.findGroupById(student.getGroupId())
                    .orElseThrow(() -> new IllegalArgumentException(String.valueOf(student.getGroupId())));

            group.deleteStudent(student);
            studentRepository.deleteStudentFromRepo(student);
            groupRepository.updateGroup(group);
    }
}
