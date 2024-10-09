package com.example.cab302simplestock.model;

public class Type {
    int typeID;
    String name;

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}
