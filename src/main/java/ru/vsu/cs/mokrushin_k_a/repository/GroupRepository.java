package ru.vsu.cs.mokrushin_k_a.repository;

import ru.vsu.cs.mokrushin_k_a.exception.BusinessException;
import ru.vsu.cs.mokrushin_k_a.exception.GroupNotFoundException;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidDataException;
import ru.vsu.cs.mokrushin_k_a.model.Group;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GroupRepository {
    private int nextIdGroup = 1;
    private final Map<Integer, Group> groups = new HashMap<>();
    private final String filePath;

    public GroupRepository(String filePath){
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
                int courseNum = Integer.parseInt(parts[0]);
                int groupId = Integer.parseInt(parts[1]);
                String groupName = parts[2];
                Group group = new Group(courseNum, groupId, groupName);
                groups.put(groupId, group);

                if(groupId >= nextIdGroup){
                    nextIdGroup = groupId + 1;
                }
            }
        } catch (IOException e) {
            throw new BusinessException("Error loading groups from file: " + e.getMessage());
        }
    }

    private void save(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for(Group g : groups.values()){
                String line = g.getCourseNumber() + ";" +
                        g.getGroupId() + ";" +
                        g.getGroupName();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new BusinessException("Error saving groups to file: " + e.getMessage());
        }
    }

    public void addGroupToRep(Group group){
        if(group == null){
            throw new InvalidDataException("Group can't be null");
        }
        if(groups.containsKey(group.getGroupId())){
            throw new InvalidDataException("Group with id " + group.getGroupId() + " is already in the list");
        }
        groups.put(group.getGroupId(), group);
        save();
    }

    public void deleteGroupById(int groupId) {
        Group group = groups.get(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }
        if (!group.isEmpty()) {
            throw new BusinessException("Can't delete a non-empty group");
        }
        groups.remove(groupId);
        save();
    }

    public Optional<Group> findGroupById(int id){
        return Optional.ofNullable(groups.get(id));
    }

    public List<Group> findAll() {
        return new ArrayList<>(groups.values());
    }

    public void updateGroup(Group group){
        if(group == null){
            throw new InvalidDataException("Group can't be null");
        }

        groups.put(group.getGroupId(), group);
        save();
    }

    public List<Group> findGroupsByCourse(int courseNumber) {
        return groups.values().stream().filter(g -> g.getCourseNumber() == courseNumber).toList();
    }


    public int generateId(){
        return nextIdGroup++;
    }
}