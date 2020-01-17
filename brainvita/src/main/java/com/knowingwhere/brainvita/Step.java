package com.knowingwhere.brainvita;

/**
 * Represents a base class step in the game. A step is moving one marble over another and removing
 * the marble that was jumped over
 */
public class Step {
    protected Coordinate from;
    protected Coordinate to;

    /**
     * Constructor
     * @param from starting coordinate of the step
     * @param to ending coordinate of the step
     */
    public Step(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    /**
     * An operation is valid if a marble is moving within the same row (horizontally) or same
     * column (vertically) over a gap of 1 marble. This operation does not take into account
     * current game configuration for assessing legality of the move. Subclasses can override
     * this method to do validation based on context of the step
     * @return whether the base step operation is valid
     */
    public boolean isValid() {
        if ((to.getX() != from.getX()) && (to.getY() != from.getY())) {
            return false;
        }

        if (to.getX() < 0 || from.getX() < 0 || to.getY() < 0 || from.getY() < 0) {
            return false;
        }

        if (to.getX() == from.getX()){
            if (to.getY() == from.getY()) {
                return false;
            } else {
                return Math.abs(to.getY() - from.getY()) == 2;
            }
        } else {
            return Math.abs(to.getX() - from.getX()) == 2;
        }
    }

    /**
     * String representation of the step
     * @return string representation of the step
     */
    @Override
    public String toString() {
        return "(" + from.getX() + ", " + from.getY()+")"+
                " ==> (" + to.getX() + ", " + to.getY()+")";
    }
}
