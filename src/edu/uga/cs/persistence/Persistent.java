package edu.uga.cs.persistence;


/**
 * Persistent
 * @author Logan Jahnke
 * @created March 18, 2017
 */
public abstract class Persistent {

    private int id;

    public Persistent() {
        this.id = -1;
    }

    public Persistent(int id) {
        this.id = id;
    }

    /**
     * Return the persistent identifier of this entity object instance. The value of -1 indicates that
     * the object has not been stored in the persistent data store, yet.
     *
     * @return persistent identifier of an entity object instance
     */
    public int getId() {
        return id;
    }

    public String getStringId() {
        return "" + this.id;
    }

    /**
     * Set the persistent identifier for this entity object.  This method is typically used by the persistence
     * subsystem when creating a proxy object for an entity object already residing in the persistent data store.
     *
     * @param id the persistent object key
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Check if this entity object has been stored in the the persistent data store (for the first time).
     * Note that the value is isPersistent() may be true, even though the entity object may need to be saved
     * in the persistent data store again, after an update to its state.
     *
     * @return true if this entity object has already been stored in the persistent data store, false otherwise.
     */
    public boolean isPersistent() {
        return id >= 0;
    }

};
