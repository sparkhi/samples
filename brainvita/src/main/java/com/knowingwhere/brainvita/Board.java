package com.knowingwhere.brainvita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Board implements StepOperation {
    private static Board board = null;
    private Space[][] spaces;
    private Board() {
        spaces = new Space[7][7];
    }
    private int marbleCount = 0;

    private static List<BrainvitaStep> allOperations = new ArrayList<>();

    private static Map<Integer, List<Integer>> validPlaces = new HashMap<>();
    static {
        validPlaces.put(0, asList(2,3,4));
        validPlaces.put(1, asList(2,3,4));
        validPlaces.put(2, asList(0,1,2,3,4,5,6));
        validPlaces.put(3, asList(0,1,2,3,4,5,6));
        validPlaces.put(4, asList(0,1,2,3,4,5,6));
        validPlaces.put(5, asList(2,3,4));
        validPlaces.put(6, asList(2,3,4));

        populateAllOperations();
    }

    private static void populateAllOperations() {
        for (int fromX = 0; fromX < 7; fromX++) {
            for (int fromY = 0; fromY < 7; fromY++) {
                for(int toX = 0; toX < 7; toX++) {
                    for (int toY = 0; toY < 7; toY++) {
                        BrainvitaStep step = new BrainvitaStep(new Coordinate(fromX, fromY), new Coordinate(toX, toY));
                        if (step.isValid()) {
                            allOperations.add(step);
                        }
                    }
                }
            }
        }
    }


    public static Board createBoard() {
        if (board == null) {
            board = new Board();
            board.setup();
        }
        return board;
    }

    private void setup() {
        for(int i : validPlaces.keySet()) {
            for (int j : validPlaces.get(i)) {
                spaces[i][j] = new Space(true);
                marbleCount++;
            }
        }
        spaces[3][3].removeMarble();
        marbleCount--;
    }

    @Override
    public void applyStep(BrainvitaStep step) {
        if (!step.isValid()) {
            throw new IllegalStateException("The requested step is invalid " + step.toString());
        }

        Coordinate middle = getMiddleCoordinate(step);
        if (!isStepPossible(step.from, middle, step.to)) {
            throw new RuntimeException("The requested step is not possible " + step.toString());
        }

        spaces[step.from.getX()][step.from.getY()].removeMarble();
        spaces[middle.getX()][middle.getY()].removeMarble();
        spaces[step.to.getX()][step.to.getY()].addMarble();
        marbleCount--;
    }

    @Override
    public void rollbackStep(BrainvitaStep step) {
        if (!step.isValid()) {
            throw new IllegalStateException("The requested step is invalid");
        }

        Coordinate middle = getMiddleCoordinate(step);
        if (!isRollbackPossible(step.from, middle, step.to)) {
            throw new RuntimeException("The requested step is not possible");
        }

        spaces[step.from.getX()][step.from.getY()].addMarble();
        spaces[middle.getX()][middle.getY()].addMarble();
        spaces[step.to.getX()][step.to.getY()].removeMarble();
        marbleCount++;
    }

    private boolean isStepPossible(Coordinate from, Coordinate middle, Coordinate to) {
        if ((spaces[from.getX()][from.getY()] == null) ||
                (spaces[from.getX()][from.getY()].hasMarble() == false)) {
            return false;
        }

        if ((spaces[to.getX()][to.getY()] == null) ||
                (spaces[to.getX()][to.getY()].hasMarble())) {
            return false;
        }

        if ((spaces[middle.getX()][middle.getY()] == null) ||
                (spaces[middle.getX()][middle.getY()].hasMarble() == false)) {
            return false;
        }

        return true;
    }

    private boolean isRollbackPossible(Coordinate from, Coordinate middle, Coordinate to) {
        if ((spaces[from.getX()][from.getY()] == null) ||
                (spaces[from.getX()][from.getY()].hasMarble())) {
            return false;
        }

        if ((spaces[to.getX()][to.getY()] == null) ||
                (spaces[to.getX()][to.getY()].hasMarble() == false)) {
            return false;
        }

        if ((spaces[middle.getX()][middle.getY()] == null) ||
                (spaces[middle.getX()][middle.getY()].hasMarble())) {
            return false;
        }

        return true;
    }

    private Coordinate getMiddleCoordinate(BrainvitaStep step) {
        if (step.from.getX() == step.to.getX()) {
            int y = (step.from.getY() + step.to.getY()) / 2;
            return new Coordinate(step.from.getX(), y);
        }

        if (step.from.getY() == step.to.getY()) {
            int x = (step.from.getX() + step.to.getX()) / 2;
            return new Coordinate(x, step.from.getY());
        }

        throw new RuntimeException("Bad coordinates in step");
    }

    public List<BrainvitaStep> getAllPossibleOperations() {
        List<BrainvitaStep> allOperationsForCurrentState = new ArrayList<>();
        for (BrainvitaStep step : allOperations) {
            Coordinate middle = getMiddleCoordinate(step);
            if (isStepPossible(step.from, middle, step.to)) {
                allOperationsForCurrentState.add(step);
            }
        }
        return allOperationsForCurrentState;
    }

    public int getMarbleCount() {
        return marbleCount;
    }
}
