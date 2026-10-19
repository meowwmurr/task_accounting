package ru.vsu.cs.mokrushin_k_a.model;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidDataException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class StudentTest {

    @Test
    void taskUpdateTasksSuccess(){
        Student student = new Student("Иванов Иван Дмитриевич", 101, 1, new HashMap<>());

        student.updateTasks(1, true);

        assertTrue(student.getTasks().get(1));
    }

    @Test
    void taskUpdateInvalidNumber(){
        Student student = new Student("Иванов Иван Дмитриевич", 101, 1, new HashMap<>());

        assertThrows(InvalidDataException.class, () -> {
            student.updateTasks(5, true);
        });
    }

    @Test
    void studentsEqualTest(){
        Student student = new Student("Иванов Иван Дмитриевич", 101, 1, new HashMap<>());
        Student student1 = new Student("Петров Олег Игоревич", 102, 1, new HashMap<>());

        assertEquals(student, student1);
    }
}
