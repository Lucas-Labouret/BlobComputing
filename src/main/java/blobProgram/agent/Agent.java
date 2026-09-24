package blobProgram.agent;

import blobProgram.BlobV;
import language.instruction.Procedure;
import language.fieldRef.boolField.BoolVRef;

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

            BoolVRef flipped = tmp(new BoolVRef());
            not(state, flipped);

            BoolVRef where = tmp(new BoolVRef());
            call(flip.where(where));

            fif(where, flipped, state, state);
        }
    }
    public Procedure flip() { return new ApplyFlip(); }
}