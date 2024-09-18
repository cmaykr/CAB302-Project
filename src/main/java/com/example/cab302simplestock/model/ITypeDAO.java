package com.example.cab302simplestock.model;

import java.util.List;

public interface ITypeDAO {

    public void addType(Type type);

    public void updateType(Type type);

    public void deleteType(Type type);

    public Type getType(String typeName);

    public List<Type> getAllTypes();
}
