package com.knowingwhere.brainvita;

public class Space {
    boolean marble = false;

    public Space(boolean marble) {
        this.marble = marble;
    }

    public boolean hasMarble() {
        return marble;
    }

    public void addMarble() {
        if (marble) {
            throw new IllegalStateException("Already contains a marble");
        }
        marble = true;
    }

    public void removeMarble() {
        if (marble == false) {
            throw new IllegalStateException("No marble to remove");
        }
        marble = false;
    }
}
