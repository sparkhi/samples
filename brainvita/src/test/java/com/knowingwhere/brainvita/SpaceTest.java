package com.knowingwhere.brainvita;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class SpaceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldAddMarbleToTheSpaceWhenAdded() {
        Space space = new Space(false);
        assertThat(space.hasMarble()).isFalse();
        space.addMarble();
        assertThat(space.hasMarble()).isTrue();
    }

    @Test
    public void shouldRemoveMarbleWhenRemoved() {
        Space space = new Space(true);
        assertThat(space.hasMarble()).isTrue();
        space.removeMarble();
        assertThat(space.hasMarble()).isFalse();
    }

    @Test
    public void shouldThrowExceptionWhenAddingANewMarbleWhenOneMarbleAlreadyExists() {
        expectedException.expect(IllegalStateException.class);
        Space space = new Space(true);
        space.addMarble();
    }

    @Test
    public void shouldThrowExceptionWhenRemovingAMarbleWhenThereIsNone() {
        expectedException.expect(IllegalStateException.class);
        Space space = new Space(false);
        space.removeMarble();
    }

}