package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class Vote extends Force {
    private final BlobV state;

    public Vote(BlobV state) {
        this.state = state;
    }

    private class Compute extends Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVeRef ve = tmp(new BoolVeRef());

            IntVRef halfNeighborCount = tmp(new IntVRef(new IntV(4)));
            redAdd(new BoolVeRef(BoolVe.ones()), halfNeighborCount);
            rShift(halfNeighborCount, halfNeighborCount, 1);

            IntVRef blobNeighborCount = tmp(new IntVRef(new IntV(4)));
            broadcast(state, ve);
            call(BlobV.send(ve, ve));
            redAdd(ve, blobNeighborCount);

            BlobV blobVotes = tmp(new BlobV());
            BlobV noneBlobVotes = tmp(new BlobV());
            gt(halfNeighborCount, blobNeighborCount, blobVotes);
            gt(blobNeighborCount, halfNeighborCount, noneBlobVotes);
            and(blobVotes, state, blobVotes);
            and(noneBlobVotes, notState, noneBlobVotes);

            or(blobVotes, noneBlobVotes, yes);
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    @Override
    protected Procedure compute(BoolVRef yes, BoolVRef no) {
        return new Compute(yes, no);
    }
}
