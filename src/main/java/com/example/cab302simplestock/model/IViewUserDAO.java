package com.example.cab302simplestock.model;

import java.util.List;

public interface IViewUserDAO {
    public void addViewUser(ViewUser viewUser);

    public void updateViewUser(ViewUser viewUser);

    public void deleteViewUser(ViewUser viewUser);

    public List<ViewUser> getAllViewUsers();
}
