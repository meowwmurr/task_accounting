package ru.vsu.cs.mokrushin_k_a.controller;

import ru.vsu.cs.mokrushin_k_a.exception.BusinessException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;
import ru.vsu.cs.mokrushin_k_a.repository.StudentRepository;
import ru.vsu.cs.mokrushin_k_a.service.GroupService;
import ru.vsu.cs.mokrushin_k_a.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CmdController {
    private final StudentService studentService;
    private final GroupService groupService;
    private final Scanner scanner;

    public CmdController(StudentService studentService, GroupService groupService, Scanner scanner) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.scanner = scanner;
    }


    public void run(){
        while (true){
            printMenu();
            int choice = readInt("Выберите действие: ");

            try {
                switch (choice) {
                    case 1 -> createGroup();
                    case 2 -> deleteGroup();
                    case 3 -> showAllGroups();
                    case 4 -> addStudent();
                    case 5 -> deleteStudent();
                    case 6 -> updateTask();
                    case 7 -> showStudentsInGroup();
                    case 0 -> {
                        System.out.println("Выход...");
                        return;
                    }
                    default -> System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (BusinessException e) {
                System.out.println("ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Учет сданных задач ===");
        System.out.println("1. Создать группу");
        System.out.println("2. Удалить группу");
        System.out.println("3. Показать все группы");
        System.out.println("4. Добавить студента");
        System.out.println("5. Удалить студента");
        System.out.println("6. Обновить статус задачи");
        System.out.println("7. Показать студентов группы");
        System.out.println("0. Выход");
        System.out.print("\nВыберите действие: ");
    }

    private void createGroup() {
        System.out.println(" === Создание новой группы === ");
        int courseNumber = readInt("Введите номер курса: ");
        System.out.print("Введите название группы (например, ПИ-21): ");
        String groupName = scanner.nextLine().trim();
        try {
            groupService.createGroup(courseNumber, groupName);
            System.out.println("Группа '" + groupName + "' успешно создана!");
        } catch (BusinessException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private void deleteGroup(){
        System.out.println(" === Удаление группы === ");
        System.out.println("  ==  Список групп == ");
        List<Group> groups = groupService.getAllGroups();

        for(Group g : groups){
            System.out.println("№ группы: " + g.getGroupId() + " | " + "Название группы: " + g.getGroupName());
        }

        if(groups.isEmpty()){
            System.out.println("Список групп пуст");
            return;
        }

        int groupId = readInt("Введите № группы для удаления (или введите 0 для отмены): ");
        if(groupId == 0) return;
        try {
            groupService.deleteGroup(groupId);
            System.out.println("Группа успешно удалена!");
        } catch (BusinessException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private void showAllGroups(){
        List<Group> groups = groupService.getAllGroups();
        if(groups.isEmpty()){
            System.out.println("Список групп пуст");
            return;
        }
        System.out.println(" === Список всех групп === ");
        for(Group g : groups){
            System.out.println(g.toString());
        }
    }

    private void showStudentsInGroup() {
        System.out.println(" === Показать студентов группы === \n");
        int groupId = readInt("Введите ID группы: ");
        try {
            List<Student> students = groupService.getStudentsFromGroup(groupId);

            if(students.isEmpty()){
                System.out.println("В этой группе никого нет");
                return;
            }
            System.out.println(" === Список студентов из группы с ID = " + groupId + " === ");
            for (Student s : students){
                System.out.println(s.toString());
            }
        } catch (BusinessException e){
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private void updateTask() {
        System.out.println(" === Обновление статуса таска === \n");
        int studentId = readInt("Введите ID студента: ");
        int taskNum = readInt("Введите № таска: ");
        boolean status = readBool("Сдал? (true/false): ");

        studentService.updateTaskStatus(studentId, taskNum, status);
        System.out.println("Статус таска успешно обновлён!");
    }

    private void deleteStudent() {
        System.out.println(" === Удаление студента === \n");
        System.out.println("  ==  Список студентов  ==  ");
        List<Student> students = studentService.getAllStudents();

        for (Student s : students) {
            System.out.println("ID: " + s.getStudentId() + " | " + s.getFullName() + " | Группа: " + s.getGroupId());
        }

        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        int studentId = readInt("Введите ID студента для удаления (или 0 для отмены): ");
        if (studentId == 0) return;
        try {
            studentService.deleteStudent(studentId);
            System.out.println("Студент с ID " + studentId + " успешно удален!");
        } catch (BusinessException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }

    }

    private void addStudent() {
        System.out.println(" === Добавление студента === ");
        System.out.print("Введите ФИО студента: ");
        String name = scanner.nextLine().trim();
        int groupId = readInt("Введите номер группы: ");
        try {
            studentService.addStudent(name, groupId);
            System.out.println("Студент успешно добавлен!");
        } catch (BusinessException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    private int readInt(String str) {
        while (true) {
            try {
                System.out.print(str);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести целое число!");
            }
        }
    }

    private boolean readBool(String str){
        while (true){
                System.out.print(str);
                String input = scanner.nextLine().trim().toLowerCase();
                if(input.equals("true") || input.equals("да") || input.equals("1")){return true;}
                if(input.equals("false") || input.equals("нет") || input.equals("0")){return false;}
            System.out.println("Введите true/false (или да/нет)");
        }
    }
}
