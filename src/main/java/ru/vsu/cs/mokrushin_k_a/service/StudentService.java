package ru.vsu.cs.mokrushin_k_a.service;

import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    GroupRepository groupRepository = new GroupRepository();
    StudentRepository studentRepository = new StudentRepository("src/main/java/ru/vsu/cs/mokrushin_k_a/repository.csv");
    public void addStudent(String fullName, int groupId){
        if(groupRepository.groupInRepository(groupId) || !fullName.isBlank()) {

        }
    }
}
