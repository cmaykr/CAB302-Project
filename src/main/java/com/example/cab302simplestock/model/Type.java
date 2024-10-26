package com.example.cab302simplestock.model;

/**
 * Simple model class for representing a type with its name.
 */
public class Type {
    private int typeID;
    private String name;

    /**
     * Constructor that sets a type with its name.
     * @param name The name of the type.
     */
    public Type(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the type.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the type.
     * @param newName The new name, must have a non-empty value.
     * @throws IllegalArgumentException If the name parameter is empty.
     */
    public void setName(String newName) throws IllegalArgumentException {
        if (newName.isEmpty())
        {
            throw new IllegalArgumentException("Type name cannot be empty.");
        }
        name = newName;
    }

    /**
     * Gets the ID for the type.
     * @return The type ID.
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * Sets a new ID for the type.
     * @param newID The new ID, must be positive, >0.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setTypeID(int newID) throws IllegalArgumentException {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Type ID cannot be negative, must be positive, >0.");
        }

        typeID = newID;
    }
}
