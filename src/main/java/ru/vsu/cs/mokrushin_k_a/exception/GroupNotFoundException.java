package ru.vsu.cs.mokrushin_k_a.exception;


public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(int groupId) {
        super("Group not found with id = " + groupId);
    }
}
