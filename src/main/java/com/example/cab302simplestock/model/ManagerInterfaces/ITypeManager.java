package com.example.cab302simplestock.model.ManagerInterfaces;

import com.example.cab302simplestock.model.Type;

import java.util.List;

/**
 * Interface defining methods used in the type manager, CRUD methods and extra methods for searching and finding specific types.
 */
public interface ITypeManager {

    /**
     * CRUD method to add a type to the database
     * @param type The type to add.
     * @return The ID value of the type in the database.
     */
    int addType(Type type);

    /**
     * CRUD method to update a type that exists in the database.
     * All values for the type model should be included in this.
     * @param type The type to update.
     */
    void updateType(Type type);

    /**
     * CRUD method to delete a type in the database.
     * If this method is called it should be assumed the type is deleted and unrecoverable.
     * Which type to delete depends on the implementation and which values are used to find the type.
     * @param type The type to delete.
     */
    void deleteType(Type type);

    /**
     * Gets all types that exist in the database.
     * @return A list of all types.
     */
    List<Type> getAllTypes();

    /**
     * Searches for and gets a specific type in the database based on the type's name.
     * @param typeName The name of the type to search for.
     * @return The type if found.
     */
    Type getType(String typeName);

    /**
     * Searches for and gets a specific type in the database based on the type's ID.
     * @param typeID The ID of the type to search for.
     * @return The type if found.
     */
    Type getTypeByID(int typeID);
}
