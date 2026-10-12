package ru.vsu.cs.mokrushin_k_a.repository;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidStudentNameException;
import ru.vsu.cs.mokrushin_k_a.model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class StudentRepository {
    private int nextId = 1;
    private final Map<Integer, Student> students = new HashMap<>();
    private final String filePath;

    public StudentRepository(String filePath){
        this.filePath = filePath;
        load();
    }

    private void load(){
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null){
                if(line.isBlank()){continue;}
                String[] parts = line.split(";");
                int studentId = Integer.parseInt(parts[0]);
                String fullName = parts[1];
                int groupId = Integer.parseInt(parts[2]);
                HashMap<Integer, Boolean> tasks = new HashMap<>();
                tasks.put(1, Boolean.parseBoolean(parts[3]));
                tasks.put(2, Boolean.parseBoolean(parts[4]));
                tasks.put(3, Boolean.parseBoolean(parts[5]));

                Student student = new Student(fullName, groupId, studentId, tasks);
                students.put(studentId, student);

                if(studentId >= nextId){
                    nextId = studentId + 1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for(Student s : students.values()){
                String line = s.getStudentId() + ";" +
                        s.getFullName() + ";" +
                        s.getGroupId() + ";" +
                        s.taskStatus(1) + ";" +
                        s.taskStatus(2) + ";" +
                        s.taskStatus(3);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudentToRep(Student student){
        if (student == null){
            throw new IllegalArgumentException("Student cant be null");
        }
        else{
            if (students.containsKey(student.getStudentId())){
                throw new InvalidStudentNameException("Student is already on this list");
            }
            /*
              распишу тут пока свои мысли по поводу присвоения id. Т.к id это final параметр у Student
              ПОЭТОМУ студент будет получать свой id уже в Сервисе при создании репы и чтения списка студентов
             */
            students.put(student.getStudentId(), student);
            save();
        }
    }

    public void deleteStudentFromRepo(Student student){
        if (student == null) throw new IllegalArgumentException();
        students.remove(student.getStudentId(), student);
        save();
    }

    public Optional<Student> findById(int id){
        return Optional.ofNullable(students.get(id));
    }

    public Map<Integer, Student> findAll(){
        return new HashMap<>(students);
    }

    public int generateId(){
        return nextId++;
    }
}
