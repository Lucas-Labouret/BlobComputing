package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Repulse extends Force {
    private final BlobV state;
    private final IntVeRef gradient;

public Repulse(BlobV state, IntVeRef gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVeRef posGrad = tmp(new BoolVeRef());
            gt(gradient, new IntVeRef(IntVe.of(1, gradient.get().n)), posGrad);

            BoolVeRef ve = tmp(new BoolVeRef());

            // Remove vertices in the blob that have a positive gradient inward
            BoolVRef toRemove = tmp(new BoolVRef());
            broadcast(state, ve);
            call(BlobV.send(ve, ve));
            and(posGrad, ve, ve);
            redOr(ve, toRemove);
            and(toRemove, state, toRemove);

            show("Repulse toRemove", toRemove);

            // Add vertices outside the blob that have a negative gradient inward
            BoolVRef toAdd = tmp(new BoolVRef());
            broadcast(state, ve);
            and(posGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toAdd);

            and(toAdd, notState, toAdd);

            show("Repulse toAdd", toAdd);

            // Build the yes and no
            or(toAdd, toRemove, yes);
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    @Override
    protected Procedure compute(BoolVRef yes, BoolVRef no) {
        return new Compute(yes, no);
    }
}
