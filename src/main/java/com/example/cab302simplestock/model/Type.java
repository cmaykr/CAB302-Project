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
        if (newName.isEmpty())
        {
            throw new IllegalArgumentException("Type name cannot be empty.");
        }
        name = newName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Type ID cannot be negative, must be positive, >0.");
        }

        typeID = newID;
    }
}
