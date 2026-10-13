package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntVe;
import language.instruction.Procedure;

/** Causes a movement of the blob in the neutral direction given by the gradient where dead ends are found. */
public class Unblock extends Force {
    private final BlobV state;
    private final IntVe gradient;

    public Unblock(BlobV state, IntVe gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class Compute extends Procedure<VoidF> {
        public Compute(BoolV yes, BoolV no) {
            BoolVe posGrad = gt(gradient, IntVe.of(1, gradient.n));
            BoolVe nullGrad = eq(gradient, IntVe.of(0, gradient.n));

            BoolV deadEnd = and(not(redOr(posGrad)), state);

            show("deadEnd", deadEnd);

            // Allow dead ends to expand through null gradient
            BoolV expand = redOr(call(BlobV.send(and(nullGrad, broadcastVe(deadEnd)))));
            BoolV toAdd = and(expand, not(state));

            // Allow dead ends to contract through null gradient
            BoolV contract = redOr(call(BlobV.send(and(nullGrad, broadcastVe(state)))));
            BoolV toRemove = and(contract, deadEnd);

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
