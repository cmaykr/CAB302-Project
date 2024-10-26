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
     * Gets the single active group for the whole application.
     * If no group is selected it will return a null value.
     * @return The Group object. Can be null
     */
    public Group getActiveGroup() {
        return selectedGroup;
    }

    /**
     * Sets a new single active group for the whole application.
     * No checks are done if the new active group is valid, this is assumed to be done before this method is called.
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
