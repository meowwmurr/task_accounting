package ru.vsu.cs.mokrushin_k_a.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import ru.vsu.cs.mokrushin_k_a.model.Student;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


class StudentRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void addStudentToRep(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        StudentRepository studentRepository = new StudentRepository(filepath);

        int newId = studentRepository.generateId();
        Student student = new Student("Иванов", 101, newId, new HashMap<>());
        studentRepository.addStudentToRep(student);
        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    void deleteStudentFromRep(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        StudentRepository studentRepository = new StudentRepository(filepath);

        int newId = studentRepository.generateId();
        Student student = new Student("Иванов", 101, newId, new HashMap<>());
        studentRepository.addStudentToRep(student);
        studentRepository.deleteStudentFromRepo(student);

        assertEquals(0, studentRepository.findAll().size());
    }

    @Test
    void updateTaskStatus(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        StudentRepository studentRepository = new StudentRepository(filepath);

        int newId = studentRepository.generateId();
        Map<Integer, Boolean> tasks = new HashMap<>();
        tasks.put(1, false);
        tasks.put(2, false);
        Student student = new Student("Иванов", 101, newId, tasks);
        studentRepository.addStudentToRep(student);

        Map<Integer, Boolean> tasks1 = new HashMap<>();
        tasks1.put(1, true);
        tasks1.put(2, true);
        Student student1 = new Student("Иванов", 101, newId, tasks1);
        studentRepository.updateStudent(student1);
        Student studentInMemory = studentRepository.findById(student.getStudentId()).orElseThrow();

        assertTrue(studentInMemory.getTasks().get(1));
        assertTrue(studentInMemory.getTasks().get(2));
    }
}
