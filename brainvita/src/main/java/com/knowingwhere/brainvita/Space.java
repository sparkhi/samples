package com.knowingwhere.brainvita;

/**
 * Represents a space on the brainvita board. A space can contain a marble or
 * it may be empty
 */
public class Space {
    boolean marble;

    /**
     * Constructor
     * @param marble whether the space has a marble or not at the beginning
     */
    public Space(boolean marble) {
        this.marble = marble;
    }

    /**
     * Returns whether the space has a marble
     * @return true if the space contains a marble, false otherwise
     */
    public boolean hasMarble() {
        return marble;
    }

    /**
     * Add a marble to this space. If there is already a marble at the space,
     * an IllegalStateException is thrown
     */
    public void addMarble() {
        if (marble) {
            throw new IllegalStateException("Already contains a marble");
        }
        marble = true;
    }

    /**
     * Remove a marble from this space. If there is no marble present at the space,
     * an IllegalStateException is thrown
     */
    public void removeMarble() {
        if (marble == false) {
            throw new IllegalStateException("No marble to remove");
        }
        marble = false;
    }
}
