package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;

import java.util.ArrayList;
import java.util.List;

public class MockGroupDAO implements IGroupDAO {
    List<Group> groups;

    public MockGroupDAO() {
        groups = new ArrayList<>();
    }

    @Override
    public int addGroup(Group group) {
        if (groups.isEmpty())
        {
            group.setGroupID(1);
        }
        else
            group.setGroupID(groups.getLast().getGroupID()+1);

        groups.add(group);

        return group.getGroupID();
    }

    @Override
    public void updateGroup(Group group) {
        int groupID = -1;
        for (Group gr: groups)
        {
            if (gr.getGroupID() == group.getGroupID())
            {
                groupID = gr.getGroupID();
                break;
            }
        }

        if (groupID != -1)
        {
            groups.set(groupID, group);
        }
    }

    @Override
    public void deleteGroup(Group group) {
        int groupID = -1;
        for (Group gr: groups)
        {
            if (gr.getGroupID() == group.getGroupID())
            {
                groupID = gr.getGroupID();
                break;
            }
        }

        if (groupID != -1)
        {
            groups.remove(groupID);
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return groups;
    }
}
