package ru.vsu.cs.mokrushin_k_a.service;

import ru.vsu.cs.mokrushin_k_a.exception.BusinessException;
import ru.vsu.cs.mokrushin_k_a.exception.GroupNotFoundException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.model.Student;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void createGroup(int courseNum, String groupName){
        if (courseNum < 1 || courseNum > 6){throw new BusinessException("Invalid course number");
        }
        Group group = new Group(courseNum, groupRepository.generateId(), groupName);
        groupRepository.addGroupToRep(group);
    }

    public void deleteGroup(int groupId){
        Group group = groupRepository.findGroupById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));

        if(!group.isEmpty()){throw new BusinessException("Can't delete a non-empty group. Expel students first.");}
        groupRepository.deleteGroupById(groupId);
    }

    public Optional<Group> getGroupById(int groupId){
        return groupRepository.findGroupById(groupId);
    }

    public List<Group> getAllGroups() {
        return new ArrayList<>(groupRepository.findAll());
    }

    public List<Group> getGroupsByCourse(int courseNumber) {
        return groupRepository.findGroupsByCourse(courseNumber);
    }

    public List<Student> getStudentsFromGroup(int groupId){
        Group group = groupRepository.findGroupById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
        return group.getStudentsList();
    }
}
