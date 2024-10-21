package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Type;

import java.util.List;

/**
 * CRUD interface for interacting with types in a database.
 */
public interface ITypeDAO {

    /**
     * Adds a new type to a database. The ID should be set by the database.
     * @param type The type to add.
     */
    public void addType(Type type);

    /**
     * Updates an type in the database, the type ID should be used to find which type to update.
     * @param type The type to update, the ID should be set.
     */
    public void updateType(Type type);

    /**
     * Deletes an type in a database, should be chosen by the type ID.
     * @param type The type to delete, the ID value should be set.
     */
    public void deleteType(Type type);

    /**
     * Gets all types in a database, should set all values in the Type model.
     * @return A list of types.
     */
    public List<Type> getAllTypes();
}
