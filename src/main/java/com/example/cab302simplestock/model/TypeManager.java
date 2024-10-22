package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;

import java.util.List;

public class TypeManager implements ITypeManager {
    private ITypeDAO typeDAO;

    public TypeManager(ITypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }
    @Override
    public int addType(Type type) {
        return typeDAO.addType(type);
    }

    @Override
    public void updateType(Type type) {
        typeDAO.updateType(type);
    }

    @Override
    public void deleteType(Type type) {
        typeDAO.deleteType(type);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeDAO.getAllTypes();
    }

    @Override
    public Type findType(String typeName) {
        return typeDAO.findTypeByName(typeName);
    }

    @Override
    public Type getTypeByID(int typeID) {
        return typeDAO.findTypeByID(typeID);
    }
}
