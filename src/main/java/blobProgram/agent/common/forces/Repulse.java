package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntVe;
import language.instruction.Procedure;

/**
 * Causes a movement of the blob in the positive direction given by the gradient.
 */
public class Repulse extends Force {
    private final BlobV state;
    private final IntVe gradient;

public Repulse(BlobV state, IntVe gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure {
        public Compute(BoolV yes, BoolV no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVe posGrad = tmp(new BoolVe());
            gt(gradient, IntVe.of(1, gradient.n), posGrad);

            BoolVe ve = tmp(new BoolVe());

            // Remove vertices in the blob that have a positive gradient inward
            BoolV toRemove = tmp(new BoolV());
            broadcast(state, ve);
            call(BlobV.send(ve, ve));
            and(posGrad, ve, ve);
            redOr(ve, toRemove);
            and(toRemove, state, toRemove);

            show("Repulse toRemove", toRemove);

            // Add vertices outside the blob that have a negative gradient inward
            BoolV toAdd = tmp(new BoolV());
            broadcast(state, ve);
            and(posGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toAdd);

            and(toAdd, notState, toAdd);

            show("Repulse toAdd", toAdd);

            // Build the yes and no
            or(toAdd, toRemove, yes);
            set(new BoolV().zeroes(), no);
        }
    }

    @Override
    protected Procedure compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
