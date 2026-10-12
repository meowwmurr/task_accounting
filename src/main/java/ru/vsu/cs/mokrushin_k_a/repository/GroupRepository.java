package ru.vsu.cs.mokrushin_k_a.repository;

import ru.vsu.cs.mokrushin_k_a.exception.GroupException;
import ru.vsu.cs.mokrushin_k_a.model.Group;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GroupRepository {
    private int nextIdGroup = 1;
    private final Map<Integer, Group> groups = new HashMap<>();

    public void addGroupToRep(Group group){
        if(group == null){throw new IllegalArgumentException();}
        if(groups.containsKey(group.getGroupId())){throw new GroupException("Group is already in the list");}
        groups.put(group.getGroupId(), group);
    }

    public void deleteGroupFromRep(Group group){
        if(group == null){throw new IllegalArgumentException();}
        if(group.isEmpty())
            groups.remove(group.getGroupId(), group);
        else {throw new GroupException("Можно удалить только пустую группу");}
    }

    public Group findGroupById(int id){
        if(!groups.containsKey(id)){throw new GroupException("Group not found in list");}
        return groups.get(id);
    }

    public boolean groupInRepository(int id){
        return groups.containsKey(id);
    }

    public Map<Integer, Group> findAll(){
        return new HashMap<>(groups);
    }

    public int generateId(){
        return nextIdGroup++;
    }
}
