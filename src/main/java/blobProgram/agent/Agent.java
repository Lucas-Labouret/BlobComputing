package blobProgram.agent;

import blobProgram.BlobV;
import language.field.boolField.BoolV;
import language.instruction.Procedure;

/**
 * An agent has a state represented as a BlobV which can evolve over time.
 * It has a Flip, which is recomputed at each iteration, and can be applied to the state to update it.
 */
public abstract class Agent {
    public final BlobV state;
    private final Flip flip;

    public Agent(BlobV state, Flip flip) {
        this.state = state;
        this.flip = flip;
    }

    /**
     * Does any computation needed before the forces and constraints are applied.
     * For example, compute a gradient once if it is needed by multiple forces.
     */
    protected Procedure precompute() {
        return new Procedure(){};
    }

    /** Computes and applies the flip to the state of the agent. */
    private class ApplyFlip extends Procedure {
        public ApplyFlip() {
            call(precompute());

            BoolV where = tmp(new BoolV());
            call(flip.where(where));

            xor(state, where, state);
        }
    }
    public Procedure flip() { return new ApplyFlip(); }
}