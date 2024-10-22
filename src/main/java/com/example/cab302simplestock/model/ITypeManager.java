package com.example.cab302simplestock.model;

import java.util.List;

public interface ITypeManager {

    int addType(Type type);

    void updateType(Type type);

    void deleteType(Type type);

    List<Type> getAllTypes();

    Type findType(String typeName);

    Type getTypeByID(int typeID);
}
