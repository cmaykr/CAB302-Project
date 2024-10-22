package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class ViewUserManager implements IViewUserManager {
    IViewUserDAO viewUserDAO;
    IUserManager userManager;

    public ViewUserManager(IViewUserDAO viewUserDAO, IUserManager userManager) {
        this.viewUserDAO = viewUserDAO;
        this.userManager = userManager;
    }

    @Override
    public int addViewUser(ViewUser viewUser) {
        return viewUserDAO.addViewUser(viewUser);
    }

    @Override
    public void updateViewUser(ViewUser viewUser) {
        viewUserDAO.updateViewUser(viewUser);
    }

    @Override
    public void deleteViewUser(ViewUser viewUser) {
        viewUserDAO.deleteViewUser(viewUser);
    }

    @Override
    public List<ViewUser> getAllViewUsers() {
        return viewUserDAO.getAllViewUsers();
    }

    @Override
    public List<User> getUsersInGroup(int groupID) {
        List<ViewUser> viewUsers = viewUserDAO.getViewUsersByGroupID(groupID);

        List<Integer> IDs = new ArrayList<>();
        for (ViewUser viewUser: viewUsers) {
            IDs.add(viewUser.getUserID());
        }

        return userManager.getUsersByIDs(IDs);
    }

    @Override
    public User findUserInGroup(int userID, int groupID) {
        return null;
    }

    @Override
    public int addUserToGroup(User user, int groupID) {
        ViewUser viewUser = new ViewUser(user.getID(), groupID);
        return viewUserDAO.addViewUser(viewUser);
    }

    @Override
    public void deleteUserFromGroup(int userID, int groupID) {

    }

    @Override
    public ViewUser findViewUser(int userID, int groupID) {
        return null;
    }
}
