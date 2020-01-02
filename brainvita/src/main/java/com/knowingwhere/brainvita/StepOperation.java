package com.knowingwhere.brainvita;

public interface StepOperation {
    void applyStep(BrainvitaStep step);
    void rollbackStep(BrainvitaStep step);
}
