package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Group;

import java.util.List;

public interface IGroupDAO {

    public int addGroup(Group group);

    public void updateGroup(Group group);

    public void deleteGroup(Group group);

    public List<Group> getAllGroups();
}
