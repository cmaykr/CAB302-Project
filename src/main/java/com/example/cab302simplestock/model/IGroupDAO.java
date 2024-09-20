package com.example.cab302simplestock.model;

import java.util.List;

public interface IGroupDAO {

    public void addGroup(Group group);

    public void updateGroup(Group group);

    public void deleteGroup(Group group);

    public List<Group> getAllGroups();
}
