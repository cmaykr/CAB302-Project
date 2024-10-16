package com.example.cab302simplestock.model;

public class GroupManager {
    private static GroupManager instance;
    private Group selectedGroup = null;

    private GroupManager() { }

    public static GroupManager getInstance() {
        if (instance == null)
            instance = new GroupManager();

        return instance;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group newGroup) {
        selectedGroup = newGroup;
    }

    public void deselectGroup() {
        selectedGroup = null;
    }
}
