package ru.vsu.cs.mokrushin_k_a.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vsu.cs.mokrushin_k_a.exception.InvalidDataException;
import ru.vsu.cs.mokrushin_k_a.model.Group;
import ru.vsu.cs.mokrushin_k_a.repository.GroupRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

class GroupServiceTest {
    @TempDir
    Path tempDir;

    @Test
    void createGroup(){
        String groupFile = tempDir.resolve("groupFile.txt").toString();

        GroupRepository groupRepository = new GroupRepository(groupFile);
        GroupService groupService = new GroupService(groupRepository);

        groupService.createGroup(1, "ПИ-201");
        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    void deleteGroup(){
        String groupFile = tempDir.resolve("groupFile.txt").toString();

        GroupRepository groupRepository = new GroupRepository(groupFile);
        GroupService groupService = new GroupService(groupRepository);

        groupService.createGroup(1, "ПИ-201");
        Group group = groupRepository.findAll().getFirst();

        groupService.deleteGroup(group.getGroupId());
        assertEquals(0, groupRepository.findAll().size());
    }
}
