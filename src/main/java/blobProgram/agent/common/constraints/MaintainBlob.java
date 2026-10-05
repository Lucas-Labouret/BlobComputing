package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.field.boolField.BoolE;
import language.field.boolField.BoolEv;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
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

    private class Verify extends Procedure {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            show("Blob / flip before", flip, Styles.FLIP);

            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BlobV emptying = tmp(new BlobV());
            BlobV filling = tmp(new BlobV());
            and(state, flip, emptying);
            and(notState, flip, filling);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            // A blob can only evolve on its frontier and border
            BlobV frontierOrBorder = tmp(new BlobV());
            BoolE frontierE = tmp(new BoolE());
            call(state.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);
            redOr(ve, frontierOrBorder);

            and(frontierOrBorder, flip, flip);
            and(state, flip, emptying);
            and(notState, flip, filling);

            show("Blob / flip after frontierOrBorder", flip, Styles.FLIP);

            // Disallow merging and splitting blobs
            BlobV mergeV = tmp(new BlobV());
            BoolE mergeE = tmp(new BoolE());
            BlobV splitV = tmp(new BlobV());
            BoolE splitE = tmp(new BoolE());
            call(state.meetV(mergeV));
            call(state.meetE(mergeE));
            call(notState.meetV(splitV));
            call(notState.meetE(splitE));

            BoolE bothFilling = tmp(new BoolE());
            broadcast(filling, ve);
            transfer(ve, ev);
            redAnd(ev, bothFilling);
            and(bothFilling, mergeE, bothFilling);

            BoolE bothEmptying = tmp(new BoolE());
            broadcast(emptying, ve);
            transfer(ve, ev);
            redAnd(ev, bothEmptying);
            and(bothEmptying, splitE, bothEmptying);

            BoolE toInspect = tmp(new BoolE());
            or(bothFilling, bothEmptying, toInspect);

            BoolV noMergeSplit = tmp(new BoolV());
            call(mutex(toInspect, priority, prioRand, noMergeSplit));
            or(noMergeSplit, splitV, noMergeSplit);
            or(noMergeSplit, mergeV, noMergeSplit);
            not(noMergeSplit, noMergeSplit);

            and(noMergeSplit, flip, flip);
            and(state, flip, emptying);
            and(notState, flip, filling);

            show("Blob / flip after wouldNotMergeOrSplit", flip, Styles.FLIP);

            // Disallow filling next to an emptying vertex, which could create a hole in the blob
            BlobV fillingNextToEmptying = tmp(new BlobV());
            broadcast(emptying, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, fillingNextToEmptying);
            and(fillingNextToEmptying, filling, fillingNextToEmptying);
            not(fillingNextToEmptying, fillingNextToEmptying);

            and(fillingNextToEmptying, flip, flip);
            and(state, flip, emptying);
            and(notState, flip, filling);

            show("Blob / flip after fillingNextToEmptying", flip, Styles.FLIP);

            // Disallow blobs from disappearing entirely
            BlobV wouldDisappearBlob = tmp(new BlobV());
            fif(flip, state, notState, wouldDisappearBlob);

            BlobV neighborsWouldBeEmpty = tmp(new BlobV());
            broadcast(wouldDisappearBlob, ve);
            call(BlobV.send(ve, ve));
            redAnd(ve, neighborsWouldBeEmpty);

            BlobV wouldDisappear = tmp(new BlobV());
            and(wouldDisappearBlob, neighborsWouldBeEmpty, wouldDisappear);
            and(wouldDisappear, emptying, wouldDisappear);
            not(wouldDisappear, wouldDisappear);

            and(wouldDisappear, flip, flip);
            and(state, flip, emptying);
            and(notState, flip, filling);

            show("Blob / flip after wouldDisappearBlob", flip, Styles.FLIP);

            // Disallow holes from disappearing entirely
            BlobV wouldBeFilled = tmp(new BlobV());
            fif(flip, notState, state, wouldBeFilled);

            BlobV neighborsWouldBeFilled = tmp(new BlobV());
            broadcast(wouldBeFilled, ve);
            call(BlobV.send(ve, ve));
            redAnd(ve, neighborsWouldBeFilled);

            BlobV wouldDisappearHole = tmp(new BlobV());
            and(wouldBeFilled, neighborsWouldBeFilled, wouldDisappearHole);
            and(wouldDisappearHole, filling, wouldDisappearHole);
            not(wouldDisappearHole, wouldDisappearHole);

            and(wouldDisappearHole, flip, flip);

            show("Blob / flip after wouldDisappearHole", flip, Styles.FLIP);
        }
    }

    @Override
    public Procedure verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
