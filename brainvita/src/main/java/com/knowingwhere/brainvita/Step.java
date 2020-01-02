package com.knowingwhere.brainvita;

public class Step {
    protected Coordinate from;
    protected Coordinate to;

    public Step(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    /**
     * An operation is valid if a marble is moving in the same horizontal or vertical direction
     * over a gap of 1 marble.
     * @return whether the step operation is valid
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

    @Override
    public String toString() {
        return "(" + from.getX() + ", " + from.getY()+")"+
                " ==> (" + to.getX() + ", " + to.getY()+")";
    }
}
