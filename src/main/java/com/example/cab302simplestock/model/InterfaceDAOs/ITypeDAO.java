package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Type;

import java.util.List;

public interface ITypeDAO {

    public void addType(Type type);

    public void updateType(Type type);

    public void deleteType(Type type);

    public List<Type> getAllTypes();
}
