package blobProgram.agent;

import language.instruction.Procedure;
import language.fieldRef.boolField.BoolVRef;

public class Agent {
    public final BoolVRef state;
    private final Flip flip;

    public Agent(BoolVRef state, Flip flip) {
        this.state = state;
        this.flip = flip;
    }

    private class ApplyFlip extends Procedure {
        public ApplyFlip() {
            BoolVRef notIn = tmp(new BoolVRef());
            not(state, notIn);

            BoolVRef where = tmp(new BoolVRef());
            call(flip.where(where));

            fif(where, notIn, state, state);
        }
    }
    public Procedure flip() { return new ApplyFlip(); }
}