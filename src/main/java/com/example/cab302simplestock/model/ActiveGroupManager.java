package com.example.cab302simplestock.model;

/**
 * Singleton class for tracking the current active group.
 */
public class ActiveGroupManager implements IActiveGroup {
    private static ActiveGroupManager instance;
    private static Group selectedGroup = null;

    private ActiveGroupManager() { }

    /**
     * Gets the instance of the singleton, if it doesn't exist it's created.
     * @return The ActiveGroup instance.
     */
    public static ActiveGroupManager getInstance() {
        if (instance == null)
            instance = new ActiveGroupManager();

        return instance;
    }

    /**
     * Gets the active group of type Group.
     * @return The Group object.
     */
    public Group getActiveGroup() {
        return selectedGroup;
    }

    /**
     * Sets a new active group.
     * @param newGroup The new active Group object.
     */
    public void setActiveGroup(Group newGroup) {
        selectedGroup = newGroup;
    }

    /**
     * Deselects the active group no so group is active, akin to resetting the singleton.
     */
    public void deselectGroup() {
        selectedGroup = null;
    }
}
