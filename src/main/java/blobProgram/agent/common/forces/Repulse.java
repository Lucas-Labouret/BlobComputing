package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntVe;
import language.instruction.Procedure;

/** Causes a movement of the blob in the positive direction given by the gradient. */
public class Repulse extends Force {
    private final BlobV state;
    private final IntVe gradient;

public Repulse(BlobV state, IntVe gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure<VoidF> {
        public Compute(BoolV yes, BoolV no) {
            BoolVe posGrad = gt(gradient, IntVe.of(1, gradient.n));

            BoolVe ve = tmp(new BoolVe());

            // Remove vertices in the blob that have a positive gradient inward
            BoolV hasPosGradInward = redOr(and(posGrad, call(BlobV.send(broadcastVe(state)))));
            BoolV toRemove = and(hasPosGradInward, state);

            show("Repulse toRemove", toRemove);

            // Add vertices outside the blob that have a negative gradient inward
            BoolV hasNegGradInward = redOr(call(BlobV.send(and(posGrad, broadcastVe(state)))));
            BoolV toAdd = and(hasNegGradInward, not(state));

            show("Repulse toAdd", toAdd);

            // Build the yes and no
            set(or(toAdd, toRemove), yes);
            set(new BoolV().zeroes(), no);

            returnValue(new VoidF());
        }
    }

    @Override
    protected Procedure<VoidF> compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
