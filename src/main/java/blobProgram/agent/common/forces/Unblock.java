package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntVe;
import language.instruction.Procedure;

public class Unblock extends Force {
    private final BlobV state;
    private final IntVe gradient;

    public Unblock(BlobV state, IntVe gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure {
        public Compute(BoolV yes, BoolV no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVe posGrad = tmp(new BoolVe());
            gt(gradient, IntVe.of(1, gradient.n), posGrad);

            BoolVe nullGrad = tmp(new BoolVe());
            eq(gradient, IntVe.of(0, gradient.n), nullGrad);

            BoolVe ve = tmp(new BoolVe());

            BoolV deadEnd = tmp(new BoolV());
            redOr(posGrad, deadEnd);
            not(deadEnd, deadEnd);
            and(deadEnd, state, deadEnd);

            show("deadEnd", deadEnd);

            // Allow dead ends to expand through null gradient
            BoolV toAdd = tmp(new BoolV());
            broadcast(deadEnd, ve);
            and(nullGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toAdd);
            and(toAdd, notState, toAdd);

            // Allow dead ends to contract through null gradient
            BoolV toRemove = tmp(new BoolV());
            broadcast(state, ve);
            and(nullGrad, ve, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, toRemove);
            and(toRemove, deadEnd, toRemove);

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
