package ru.vsu.cs.mokrushin_k_a.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vsu.cs.mokrushin_k_a.model.Group;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.util.HashMap;

class GroupRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void addGroupToRep(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        GroupRepository groupRepository = new GroupRepository(filepath);
        Group group = new Group(1, 101, "ПИ-202");
        groupRepository.addGroupToRep(group);

        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    void deleteGroupFromRep(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        GroupRepository groupRepository = new GroupRepository(filepath);
        Group group = new Group(1, 101, "ПИ-202");
        groupRepository.addGroupToRep(group);
        groupRepository.deleteGroupById(group.getGroupId());

        assertEquals(0, groupRepository.findAll().size());
    }

    @Test
    void updateGroup(){
        String filepath = tempDir.resolve("testStudent.txt").toString();
        GroupRepository groupRepository = new GroupRepository(filepath);
        Group group = new Group(1, 101, "ПИ-202");
        groupRepository.addGroupToRep(group);

        Group newGroup = new Group(1, 101, "ПИ-202-new");
        groupRepository.updateGroup(newGroup);
        Group groupInMemory = groupRepository.findGroupById(group.getGroupId()).orElseThrow();

        assertEquals("ПИ-202-new", groupInMemory.getGroupName());
    }
}
