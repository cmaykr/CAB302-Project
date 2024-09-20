package com.example.cab302simplestock.model;

public class Category {
    String listName;
    String groupName;

    public Category(String listName, String groupName) {
        this.listName = listName;
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return listName;
    }

    public void setCategoryName(String listName) {
        this.listName = listName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
