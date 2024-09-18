package com.example.cab302simplestock.model;

import java.util.List;

public interface IGroupDAO {

    public void addGroup(Group group);

    public void updateGroup(Group group);

    public void deleteGroup(Group group);

    public Group getGroup(String groupName);

    public User getOwner(Group group);

    public List<Group> getAllGroups();

    public User getViewUser(Group group, User user);

    public List<User> getAllViewUsers(Group Group);

    public void addCategory(Group group, Category category);

    public void updateCategory(Category category);

    public void deleteCategory(Category category);

    public Category getCategory(String groupName, String categoryName);

    public List<Category> getAllCategories(Group group);

    public void addItem(Group group, Category category, Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public Item getItem(int itemID);

    public List<Item> getAllGroupItems(Group group);

    public List<Item> getAllCategoryItems(Category category);
}
