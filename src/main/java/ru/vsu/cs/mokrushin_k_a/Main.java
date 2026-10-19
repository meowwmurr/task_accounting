package ru.vsu.cs.mokrushin_k_a;

import ru.vsu.cs.mokrushin_k_a.controller.CmdController;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;
import ru.vsu.cs.mokrushin_k_a.service.GroupService;
import ru.vsu.cs.mokrushin_k_a.service.StudentService;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
            String studentsFilePath = "src/data/studentRepository.csv";
            String groupsFilePath = "src/data/groupRepository.csv";
            StudentRepository studentRepository = new StudentRepository(studentsFilePath);
            GroupRepository groupRepository = new GroupRepository(groupsFilePath);

            for (Student student : studentRepository.findAll()) {
                groupRepository.findGroupById(student.getGroupId())
                        .ifPresent(group -> group.addStudent(student));
            }

            StudentService studentService = new StudentService(studentRepository, groupRepository);
            GroupService groupService = new GroupService(groupRepository);
            Scanner scanner = new Scanner(System.in);
            CmdController controller = new CmdController(studentService, groupService, scanner);
            controller.run();
        }
}