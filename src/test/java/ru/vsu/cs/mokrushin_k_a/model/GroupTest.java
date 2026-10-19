package ru.vsu.cs.mokrushin_k_a.model;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidDataException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Group group;
    private Student student;


    @Test
    void addStudent(){
        group = new Group(1, 101, "ПИ-1001");
        student = new Student("Иванов", 101, 1, new HashMap<>());
        group.addStudent(student);
        assertEquals(1, group.getStudentsCount());
    }

    @Test
    void addStudentTwice(){
        group = new Group(1, 101, "ПИ-1001");
        student = new Student("Иванов", 101, 1, new HashMap<>());
        group.addStudent(student);
        assertThrows(InvalidDataException.class, () ->{
            group.addStudent(student);
        });
    }

    @Test
    void deleteStudent(){
        group = new Group(1, 101, "ПИ-1001");
        student = new Student("Иванов", 101, 1, new HashMap<>());
        group.addStudent(student);
        group.deleteStudent(student);
        assertEquals(0, group.getStudentsCount());
    }
}
