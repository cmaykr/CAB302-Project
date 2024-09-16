package com.example.cab302simplestock.model;

public class List {
    String listName;
    String groupName;

    public List(String listName, String groupName) {
        this.listName = listName;
        this.groupName = groupName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
