package com.example.cab302simplestock.model.ManagerInterfaces;

import com.example.cab302simplestock.model.Group;

/**
 * Interface for managing the active group.
 * Only defined methods are getting the active group and setting it, to allow for a wide variety of implementations for managing the active group.
 */
public interface IActiveGroup {

    /**
     * Gets the active group of type Group.
     * If no group is selected it will return a null value.
     * @return The Group object. Can be null
     */
    Group getActiveGroup();

    /**
     * Sets a new active group.
     * No checks are done if the new active group is valid, this is assumed to be done before this method is called.
     * @param group The new active Group object.
     */
    void setActiveGroup(Group group);
}
