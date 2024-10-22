package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.ViewUser;

import java.util.List;

public class MockViewUserDAO implements IViewUserDAO {
    @Override
    public void addViewUser(ViewUser viewUser) {

    }

    @Override
    public void updateViewUser(ViewUser viewUser) {

    }

    @Override
    public void deleteViewUser(ViewUser viewUser) {

    }

    @Override
    public List<ViewUser> getAllViewUsers() {
        return List.of();
    }
}
