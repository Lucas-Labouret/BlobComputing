package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Shrink extends Force {
    private final BlobV state;
    private final IntVeRef gradient;

    public Shrink(BlobV state, IntVeRef gradient) {
        this.state = state;
        this.gradient = gradient;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            BlobV frontier = new BlobV();
            call(state.frontierV(frontier));

            BoolVeRef posOrNulGrad = tmp(new BoolVeRef());
            set(gradient.get().getBits()[0], posOrNulGrad);
            not(posOrNulGrad, posOrNulGrad);

            BlobV frontierIn = new BlobV();
            BoolVeRef ve = tmp(new BoolVeRef());
            broadcast(frontier, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, frontierIn);
            and(state, frontierIn, frontierIn);

            BoolVeRef posGradInward = tmp(new BoolVeRef());
            BoolVRef hasPosGradInward = tmp(new BoolVRef());
            call(BlobV.send(posOrNulGrad, posGradInward));
            broadcast(state, ve);
            and(posGradInward, ve, posGradInward);
            call(BlobV.send(posGradInward, posGradInward));
            redOr(posGradInward, hasPosGradInward);
            and(hasPosGradInward, frontierIn, yes);

            broadcast(yes, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, no);
            and(no, frontier, no);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
