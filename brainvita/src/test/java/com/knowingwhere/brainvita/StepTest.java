package com.knowingwhere.brainvita;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepTest {
    @Test
    public void shouldReturnTheOperationAsInvalidWhenTheStepIsDiagonal() {
        Coordinate from = new Coordinate(10, 10);
        Coordinate to = new Coordinate(12,12);
        Step operation = new Step(from, to);
        assertThat(operation.isValid()).isFalse();
    }

    @Test
    public void shouldReturnTheOperationAsInvalidWhenTheStepIsAtSamePlace() {
        Coordinate from = new Coordinate(10, 10);
        Coordinate to = new Coordinate(10,10);
        Step operation = new Step(from, to);
        assertThat(operation.isValid()).isFalse();
    }

    @Test
    public void shouldReturnTheOperationAsInvalidWhenTheStepIsMoreThan2Spaces() {
        Coordinate from = new Coordinate(10, 10);
        Coordinate to = new Coordinate(10,5);
        Step operation = new Step(from, to);
        assertThat(operation.isValid()).isFalse();
    }

    @Test
    public void shouldReturnTheOperationAsValidWhenTheStepIsExactlyOver1Place() {
        Coordinate from = new Coordinate(10, 10);
        Coordinate to = new Coordinate(10,12);
        Step operation = new Step(from, to);
        assertThat(operation.isValid()).isTrue();
    }

    @Test
    public void shouldIndicateInvalidIfEitherFromOrToIsNegative() {
        Coordinate from = new Coordinate(10, -1);
        Coordinate to = new Coordinate(10,1);
        Step operation = new Step(from, to);
        assertThat(operation.isValid()).isFalse();
    }
}