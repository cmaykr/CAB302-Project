package com.example.cab302simplestock.model.Managers;

import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;
import com.example.cab302simplestock.model.ManagerInterfaces.ITypeManager;
import com.example.cab302simplestock.model.Type;

import java.util.List;

/**
 * Implementation of the typeManager interface using a typeDAO for the managing of types.
 * Uses dependency injection to get the type DAO.
 */
public class TypeManager implements ITypeManager {
    private ITypeDAO typeDAO;

    /**
     * Constructs a typeManager with an injected type DAO.
     * @param typeDAO The type DAO to inject.
     */
    public TypeManager(ITypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }

    /**
     * Implementation of CRUD method to add a new type. All values for the type model needs to have a value, except for the ID value which is set by the database.
     * @param type The type to add.
     * @return The ID the type has in the database.
     */
    @Override
    public int addType(Type type) {
        return typeDAO.addType(type);
    }

    /**
     * Updates an existing type in the database, all values for the type are overwritten by this method.
     * @param type The type to update, the ID value should be set to the type to update in the database.
     */
    @Override
    public void updateType(Type type) {
        typeDAO.updateType(type);
    }

    /**
     * Deletes a type from the database, only the ID value for the type to delete needs to be set.
     * If this method is called it should be assumed the type is gone and unrecoverable.
     * @param type The type to delete
     */
    @Override
    public void deleteType(Type type) {
        typeDAO.deleteType(type);
    }

    /**
     * Gets all types that exist in the database.
     * @return A list of all types.
     */
    @Override
    public List<Type> getAllTypes() {
        return typeDAO.getAllTypes();
    }

    /**
     * Finds the type in a database that has the exact same name as the parameter one.
     * If the type doesn't exist it's created.
     * @param typeName The name of the type to search for.
     * @return The type if found, otherwise null.
     */
    @Override
    public Type getType(String typeName) {
        Type type = typeDAO.findTypeByName(typeName);
        if (type == null)
        {
            Type newType = new Type(typeName);
            int ID = typeDAO.addType(newType);
            return typeDAO.findTypeByID(ID);
        }
        return type;
    }

    /**
     * Finds the type in a database that has the same ID as the parameter one.
     * @param typeID The ID of the type to search for.
     * @return The type if found, otherwise null.
     */
    @Override
    public Type getTypeByID(int typeID) {
        return typeDAO.findTypeByID(typeID);
    }
}
