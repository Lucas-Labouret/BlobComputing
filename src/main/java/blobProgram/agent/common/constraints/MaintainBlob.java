package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.fieldRef.boolField.BoolERef;
import language.fieldRef.boolField.BoolEvRef;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;
import ui.display.Styles;

public class MaintainBlob extends Constraint {
    public MaintainBlob(BlobV blob) {
        super(blob);
    }

    private class Verify extends Procedure {
        public Verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
            show("Blob / flip before", flip, Styles.FLIP);

            BlobV notState = tmp(new BlobV());
            not(state, notState);

            BlobV emptying = tmp(new BlobV());
            BlobV filling = tmp(new BlobV());
            and(state, flip, emptying);
            and(notState, flip, filling);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            // A blob can only evolve on its frontier and border
            BlobV frontierOrBorder = tmp(new BlobV());
            BoolERef frontierE = tmp(new BoolERef());
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
            BoolERef mergeE = tmp(new BoolERef());
            BlobV splitV = tmp(new BlobV());
            BoolERef splitE = tmp(new BoolERef());
            call(state.meetV(mergeV));
            call(state.meetE(mergeE));
            call(notState.meetV(splitV));
            call(notState.meetE(splitE));

            BoolERef bothFilling = tmp(new BoolERef());
            broadcast(filling, ve);
            transfer(ve, ev);
            redAnd(ev, bothFilling);
            and(bothFilling, mergeE, bothFilling);

            BoolERef bothEmptying = tmp(new BoolERef());
            broadcast(emptying, ve);
            transfer(ve, ev);
            redAnd(ev, bothEmptying);
            and(bothEmptying, splitE, bothEmptying);

            BoolERef toInspect = tmp(new BoolERef());
            or(bothFilling, bothEmptying, toInspect);

            BoolVRef noMergeSplit = tmp(new BoolVRef());
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
    public Procedure verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
