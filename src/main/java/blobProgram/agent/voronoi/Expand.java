package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Expand extends Force {
    private final BlobV state;
    private final IntVeRef gradient;

    public Expand(BlobV state, IntVeRef gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            set(new BoolVRef(BoolV.zeroes()), no);

            BlobV frontier = new BlobV();
            call(state.frontierV(frontier));

            BoolVeRef posGrad = tmp(new BoolVeRef());
            gt(gradient, new IntVeRef(IntVe.of(1, gradient.get().n)), posGrad);

            BoolVeRef ve = tmp(new BoolVeRef());
            broadcast(state, ve);
            and(posGrad, ve, ve);
            call(BlobV.send(ve, ve));

            redOr(ve, yes);
            and(yes, frontier, yes);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
