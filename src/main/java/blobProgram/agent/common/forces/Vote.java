package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.instruction.Procedure;

/** Fill a vertex if most of its neighbors are filled, or empty it if most of its neighbors are empty. */
public class Vote extends Force {
    private final BlobV state;

    public Vote(BlobV state) {
        this.state = state;
    }

    private class Compute extends Procedure<VoidF> {
        public Compute(BoolV yes, BoolV no) {
            IntV halfNeighborCount = rShift(redAdd(new BoolVe().ones(), 4), 1);
            IntV blobNeighborCount = redAdd(call(BlobV.send(broadcastVe(state))), 4);

            BoolV blobVotes = and(gt(halfNeighborCount, blobNeighborCount), state);
            BoolV noneBlobVotes = and(gt(blobNeighborCount, halfNeighborCount), not(state));

            set(or(blobVotes, noneBlobVotes), yes);
            set(new BoolV().zeroes(), no);

            returnValue(new VoidF());
        }
    }

    @Override
    protected Procedure<VoidF> compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
