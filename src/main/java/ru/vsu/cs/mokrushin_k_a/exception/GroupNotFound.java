package ru.vsu.cs.mokrushin_k_a.exception;


public class GroupNotFound extends RuntimeException {
    public GroupNotFound(int groupId) {
        super("Group not found with id = " + groupId);
    }
}
