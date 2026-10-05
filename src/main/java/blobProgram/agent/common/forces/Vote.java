package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.instruction.Procedure;

/**
 * Fill a vertex if most of its neighbors are filled, or empty it if most of its neighbors are empty.
 */
public class Vote extends Force {
    private final BlobV state;

    public Vote(BlobV state) {
        this.state = state;
    }

    private class Compute extends Procedure {
        public Compute(BoolV yes, BoolV no) {
            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BoolVe ve = tmp(new BoolVe());

            IntV halfNeighborCount = tmp(new IntV(4));
            redAdd(new BoolVe().ones(), halfNeighborCount);
            rShift(halfNeighborCount, halfNeighborCount, 1);

            IntV blobNeighborCount = tmp(new IntV(4));
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
            set(new BoolV().zeroes(), no);
        }
    }

    @Override
    protected Procedure compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
