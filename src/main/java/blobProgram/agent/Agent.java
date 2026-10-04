package blobProgram.agent;

import blobProgram.BlobV;
import language.field.boolField.BoolV;
import language.instruction.Procedure;

public abstract class Agent {
    public final BlobV state;
    private final Flip flip;

    public Agent(BlobV state, Flip flip) {
        this.state = state;
        this.flip = flip;
    }

    protected Procedure precompute() {
        return new Procedure(){{
            print(""); // Temporary hack until I handle empty procedures better
        }};
    }

    private class ApplyFlip extends Procedure {
        public ApplyFlip() {
            call(precompute());

            BoolV flipped = tmp(new BoolV());
            not(state, flipped);

            BoolV where = tmp(new BoolV());
            call(flip.where(where));

            fif(where, flipped, state, state);
        }
    }
    public Procedure flip() { return new ApplyFlip(); }
}