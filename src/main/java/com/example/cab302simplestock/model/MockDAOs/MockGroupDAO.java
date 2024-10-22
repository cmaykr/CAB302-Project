package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;

import java.util.List;

public class MockGroupDAO implements IGroupDAO {
    @Override
    public int addGroup(Group group) {
        return 0;
    }

    @Override
    public void updateGroup(Group group) {

    }

    @Override
    public void deleteGroup(Group group) {

    }

    @Override
    public List<Group> getAllGroups() {
        return List.of();
    }
}
