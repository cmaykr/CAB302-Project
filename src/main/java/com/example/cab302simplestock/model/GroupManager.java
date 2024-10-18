package com.example.cab302simplestock.model;

public class GroupManager {
    private static GroupManager instance;
    private static Group selectedGroup = null;

    private GroupManager() { }

    public static GroupManager getInstance() {
        if (instance == null)
            instance = new GroupManager();

        return instance;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public static void setSelectedGroup(Group newGroup) {
        selectedGroup = newGroup;
    }

    public static void deselectGroup() {
        selectedGroup = null;
    }
}
