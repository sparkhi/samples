package com.knowingwhere.brainvita;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrainvitaStepTest {
    @Test
    public void shouldReturnTheOperationAsInvalidWhenTheStepIsMoreThan2Spaces() {
        Coordinate from = new Coordinate(1, 1);
        Coordinate to = new Coordinate(1,3);
        Step operation = new BrainvitaStep(from, to);
        assertThat(operation.isValid()).isFalse();
    }

}