package ru.vsu.cs.mokrushin_k_a.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidDataException;
import static org.junit.jupiter.api.Assertions.*;

import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;

import java.nio.file.Path;


class StudentServiceTest {
    @TempDir
    Path tempDir;

    @Test
    void addStudent(){
        String studentFile = tempDir.resolve("testStudent.txt").toString();
        String groupFile = tempDir.resolve("testGroup.txt").toString();

        StudentRepository studentRepository = new StudentRepository(studentFile);
        GroupRepository groupRepository = new GroupRepository(groupFile);
        StudentService studentService = new StudentService(studentRepository, groupRepository);

        Group group = new Group(1, 201, "ПИ-201");
        groupRepository.addGroupToRep(group);
        studentService.addStudent("Иванов", group.getGroupId());

        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    void deleteStudent(){
        String studentFile = tempDir.resolve("testStudent.txt").toString();
        String groupFile = tempDir.resolve("testGroup.txt").toString();

        StudentRepository studentRepository = new StudentRepository(studentFile);
        GroupRepository groupRepository = new GroupRepository(groupFile);
        StudentService studentService = new StudentService(studentRepository, groupRepository);

        Group group = new Group(1, 201, "ПИ-201");
        groupRepository.addGroupToRep(group);
        studentService.addStudent("Иванов", group.getGroupId());
        Student student = studentRepository.findAll().get(0);
        int id = student.getStudentId();
        studentService.deleteStudent(student.getStudentId());

        assertTrue(studentRepository.findById(id).isEmpty());
    }
}
