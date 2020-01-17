package com.knowingwhere.brainvita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Represents a step for the brainvita game.
 */
public class BrainvitaStep extends Step {

    private static Map<Integer, List<Integer>> validPlaces = new HashMap<>();
    static {
        validPlaces.put(0, asList(2,3,4));
        validPlaces.put(1, asList(2,3,4));
        validPlaces.put(2, asList(0,1,2,3,4,5,6));
        validPlaces.put(3, asList(0,1,2,3,4,5,6));
        validPlaces.put(4, asList(0,1,2,3,4,5,6));
        validPlaces.put(5, asList(2,3,4));
        validPlaces.put(6, asList(2,3,4));
    }


    /**
     * Constructor
     * @param from starting coordinate of the step
     * @param to ending coordinate of the step
     */
    public BrainvitaStep(Coordinate from, Coordinate to) {
        super(from, to);
    }

    /**
     * Returns whether the step is valid within the current layout of the board
     * @return whether the step can be legally performed in the context of current board layout
     */
    public boolean isValid() {
        if (super.isValid() == false) {
            return false;
        }

        if (validPlaces.get(from.getX()).contains(from.getY()) &&
                validPlaces.get(to.getX()).contains(to.getY())) {
            return true;
        }

        return false;
    }
}
