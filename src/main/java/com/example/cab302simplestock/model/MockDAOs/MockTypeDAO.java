package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;
import com.example.cab302simplestock.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockTypeDAO implements ITypeDAO {
    List<Type> types;

    public MockTypeDAO() {
        types = new ArrayList<>();
    }

    @Override
    public int addType(Type type) {
        if (types.isEmpty())
        {
            type.setTypeID(1);
        }
        else
            type.setTypeID(types.getLast().getTypeID()+1);

        types.add(type);

        return type.getTypeID();
    }

    @Override
    public void updateType(Type type) {
        int groupID = -1;
        for (Type gr: types)
        {
            if (gr.getTypeID() == type.getTypeID())
            {
                groupID = gr.getTypeID();
                break;
            }
        }

        if (groupID != -1)
        {
            for (int i=0; i<types.size();i++) {
                if (types.get(i).getTypeID() == groupID)
                {
                    types.set(i, type);
                }
            }
        }
    }

    @Override
    public void deleteType(Type type) {
        int typeID = -1;
        for (Type gr: types)
        {
            if (gr.getTypeID() == type.getTypeID())
            {
                typeID = gr.getTypeID();
                break;
            }
        }

        if (typeID != -1)
        {
            for (Type ty:types) {
                if (ty.getTypeID() == typeID)
                {
                    types.remove(ty);
                    break;
                }
            }
        }
    }

    @Override
    public List<Type> getAllTypes() {
        return types;
    }

    @Override
    public Type findTypeByName(String typeName) {
        for (Type type: types) {
            if (Objects.equals(type.getName(), typeName))
            {
                return type;
            }
        }
        return null;
    }

    @Override
    public Type findTypeByID(int typeID) {
        for (Type type: types)
        {
            if (type.getTypeID() == typeID)
            {
                return type;
            }
        }
        return null;
    }
}
