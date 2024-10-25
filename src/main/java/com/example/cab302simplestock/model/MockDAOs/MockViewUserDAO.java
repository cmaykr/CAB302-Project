package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.ViewUser;

import java.util.ArrayList;
import java.util.List;

public class MockViewUserDAO implements IViewUserDAO {
    List<ViewUser> viewUsers;

    public MockViewUserDAO() {
        viewUsers = new ArrayList<>();
    }

    @Override
    public int addViewUser(ViewUser viewUser) {
        if (viewUsers.isEmpty())
        {
            viewUser.setID(1);
        }
        else
            viewUser.setID(viewUsers.getLast().getGroupID()+1);

        viewUsers.add(viewUser);

        return viewUser.getID();
    }

    @Override
    public void updateViewUser(ViewUser viewUser) {
        for (int i=0;i<viewUsers.size(); i++) {
            if (viewUsers.get(i).getID() == viewUser.getID()) {
                viewUsers.set(i, viewUser);
            }
        }
    }

    @Override
    public void deleteViewUser(ViewUser viewUser) {
        viewUsers.removeIf(viewUse -> viewUse.getID() == viewUser.getID());
    }

    @Override
    public List<ViewUser> getAllViewUsers() {
        return viewUsers;
    }

    @Override
    public List<ViewUser> getViewUsersByGroupID(int groupID) {
        List<ViewUser> viewUsersInGroup = new ArrayList<>();
        for (ViewUser viewUser: viewUsers) {
            if (viewUser.getGroupID() == groupID)
            {
                viewUsersInGroup.add(viewUser);
            }
        }
        return viewUsersInGroup;
    }
}
