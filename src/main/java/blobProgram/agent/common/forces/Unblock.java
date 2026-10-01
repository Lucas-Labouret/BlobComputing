package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Unblock extends Force {
    private final BlobV state;
    private final IntVeRef gradient;

    public Unblock(BlobV state, IntVeRef gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVeRef posGrad = tmp(new BoolVeRef());
            gt(gradient, new IntVeRef(IntVe.of(1, gradient.get().n)), posGrad);

            BoolVeRef nullGrad = tmp(new BoolVeRef());
            eq(gradient, new IntVeRef(IntVe.of(0, gradient.get().n)), nullGrad);

            BoolVeRef ve = tmp(new BoolVeRef());

            BoolVRef deadEnd = tmp(new BoolVRef());
            redOr(posGrad, deadEnd);
            not(deadEnd, deadEnd);
            and(deadEnd, state, deadEnd);

            show("deadEnd", deadEnd);

            // Allow dead ends to expand through null gradient
            BoolVRef toAdd = tmp(new BoolVRef());
            broadcast(deadEnd, ve);
            and(nullGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toAdd);
            and(toAdd, notState, toAdd);

            // Allow dead ends to contract through null gradient
            BoolVRef toRemove = tmp(new BoolVRef());
            broadcast(state, ve);
            and(nullGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toRemove);
            and(toRemove, deadEnd, toRemove);

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
