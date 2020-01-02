package com.knowingwhere.brainvita;

import java.util.List;
import java.util.Stack;

public class BrainvitaSolverApp {
    private Stack<BrainvitaStep> stepStack = new Stack<>();
    private Board board = null;

    public static void main(String args[]) {
        BrainvitaSolverApp app = new BrainvitaSolverApp();
        app.printSteps();
    }

    private void printSteps() {
        board = Board.createBoard();
        List<BrainvitaStep> allOperations = board.getAllPossibleOperations();

        boolean done = traverse(allOperations, 1);
        if (done) {
            while (!stepStack.isEmpty()) {
                BrainvitaStep step = stepStack.pop();
                System.out.println(step.toString());
            }
        }
    }

    private boolean traverse(List<BrainvitaStep> allOperations, int iteration) {
        for(BrainvitaStep step : allOperations) {
            stepStack.push(step);
            board.applyStep(step);
            int nextVal = iteration + 1;
            List<BrainvitaStep> nextOperations = board.getAllPossibleOperations();
            if (nextOperations.size() == 0) {
                if (board.getMarbleCount() == 1) {
                    return true;
                } else {
                    BrainvitaStep lastStep = stepStack.pop();
                    board.rollbackStep(lastStep);
                }
            } else {
                boolean result = traverse(nextOperations, nextVal);
                if (result == true) {
                    return true;
                } else {
                    BrainvitaStep lastStep = stepStack.pop();
                    board.rollbackStep(lastStep);
                }
            }
        }
        return false;
    }
}
