package com.knowingwhere.brainvita;

/**
 * All coordinates are positive going right and down
 * e.g.
 * (0,0)
 *    +-------------
 *    |          .(5,1)
 *    |
 *    |.(1,3)
 *    |
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructor
     * @param x 0 based value along horizontal axis increasing from left to right
     * @param y 0 based value along vertical axis increasing from top to bottom
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
