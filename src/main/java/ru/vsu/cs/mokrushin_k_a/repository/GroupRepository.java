package ru.vsu.cs.mokrushin_k_a.repository;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GroupRepository {
    private int nextIdGroup = 1;
    private final Map<Integer, Group> groups = new HashMap<>();
    private final String filePath;

    public GroupRepository(String filePath){
        this.filePath = filePath;
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

                if(groupId >= nextIdGroup){
                    nextIdGroup = groupId + 1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    public void addGroupToRep(Group group){
        if(group == null){throw new IllegalArgumentException();}
        if(groups.containsKey(group.getGroupId())){throw new GroupException("Group is already in the list");}
        groups.put(group.getGroupId(), group);
        save();
    }

    public void deleteGroupFromRep(Group group){
        if(group == null){throw new IllegalArgumentException();}
        if(group.isEmpty()) {
            groups.remove(group.getGroupId(), group);
            save();
        }
        else {throw new GroupException("Можно удалить только пустую группу");}
    }

    public Group findGroupById(int id){
        if(!groups.containsKey(id)){throw new GroupException("Group not found in list");}
        return groups.get(id);
    }

    public Map<Integer, Group> findAll(){
        return new HashMap<>(groups);
    }

    public void updateGroup(Group group){
        if(group == null){throw new IllegalArgumentException("Group can't be null");}

        groups.put(group.getGroupId(), group);
        save();
    }

    public boolean groupInList(int groupId){
        return groups.containsKey(groupId);
    }

    public int generateId(){
        return nextIdGroup++;
    }
}
