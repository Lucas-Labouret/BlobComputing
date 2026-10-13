package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.field.VoidF;
import language.field.boolField.BoolE;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;
import ui.display.Styles;

/**
 * Constraint that maintains the integrity of a blob.
 * Blobs do not merge, split, or disappear entirely,
 * only change on their frontier and border,
 * and have a constant number of holes.
 */
public class MaintainBlob extends Constraint {
    private final BlobV state;

    public MaintainBlob(BlobV state) {
        this.state = state;
    }

    private class Verify extends Procedure<VoidF> {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            show("Blob / flip before", flip, Styles.FLIP);

            BlobV notState = tmp(new BlobV());
            set(not(state), notState);

            BoolV emptying = and(state, flip);
            BoolV filling = and(notState, flip);

            // A blob can only evolve on its frontier and border
            BoolV frontierOrBorder = redOr(transfer(broadcastEv(call(state.frontierE()))));

            set(and(frontierOrBorder, flip), flip);
            set(and(state, flip), emptying);
            set(and(notState, flip), filling);

            show("Blob / flip after frontierOrBorder", flip, Styles.FLIP);

            // Disallow merging and splitting blobs
            BoolV mergeV = call(state.meetV());
            BoolE mergeE = call(state.meetE());
            BoolV splitV = call(notState.meetV());
            BoolE splitE = call(notState.meetE());

            BoolE bothFilling = and(mergeE, redAnd(transfer(broadcastVe(filling))));
            BoolE bothEmptying = and(splitE, redAnd(transfer(broadcastVe(emptying))));

            BoolV mutex = call(mutex(or(bothFilling, bothEmptying), priority, prioRand));
            BoolV noMergeSplit = not(or(mutex, or(splitV, mergeV)));

            set(and(noMergeSplit, flip), flip);
            set(and(state, flip), emptying);
            set(and(notState, flip), filling);

            show("Blob / flip after wouldNotMergeOrSplit", flip, Styles.FLIP);

            // Disallow filling next to an emptying vertex, which could create blob
            BoolV nextToEmptying = redOr(call(BlobV.send(broadcastVe(emptying))));
            BoolV fillingNextToEmptying = and(filling, nextToEmptying);

            set(and(not(fillingNextToEmptying), flip), flip);
            set(and(state, flip), emptying);
            set(and(notState, flip), filling);

            show("Blob / flip after fillingNextToEmptying", flip, Styles.FLIP);

            // Disallow blobs from disappearing entirely
            BoolV wouldBeEmpty = xor(flip, notState);
            BoolV neighborsWouldBeEmpty = redAnd(call(BlobV.send(broadcastVe(wouldBeEmpty))));
            BoolV wouldDisappear = and(and(wouldBeEmpty, neighborsWouldBeEmpty), emptying);

            set(and(not(wouldDisappear), flip), flip);
            set(and(state, flip), emptying);
            set(and(notState, flip), filling);

            show("Blob / flip after wouldBeEmpty", flip, Styles.FLIP);

            // Disallow holes from disappearing entirely
            BoolV wouldBeFilled = xor(flip, state);
            BoolV neighborsWouldBeFilled = redAnd(call(BlobV.send(broadcastVe(wouldBeFilled))));
            BoolV wouldDisappearHole = and(and(wouldBeFilled, neighborsWouldBeFilled), filling);

            set(and(not(wouldDisappearHole), flip), flip);

            show("Blob / flip after wouldDisappearHole", flip, Styles.FLIP);

            returnValue(new VoidF());
        }
    }

    @Override
    public Procedure<VoidF> verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
