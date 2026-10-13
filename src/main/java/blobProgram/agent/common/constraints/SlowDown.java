package blobProgram.agent.common.constraints;

import blobProgram.agent.Constraint;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

/** Block all movement except for every factor-th time. This is used to slow down the movement of the blob. */
public class SlowDown extends Constraint {
    private final int nbits;
    private final int factor;

    private final IntV counter;

    public SlowDown(int factor, int nbits) {
        this.factor = factor;
        this.nbits = nbits;
        this.counter = IntV.of(factor-1, nbits);
    }

//    private final BlobV blob;
//    private final IntVe gradient;
//
//    public SlowDown(BlobV blob, IntVe gradient) {
//        this.blob = blob;
//        this.gradient = gradient;
//    }

    private class Verify extends Procedure<VoidF> {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            BoolV keepFlip = eq(counter, IntV.of(factor-1, nbits));
            set(and(keepFlip, flip), flip);

            IntV nextCounter = add(counter, IntV.of(1, nbits));
            set(fif(keepFlip, IntV.of(0, nbits), nextCounter), counter);

            returnValue(new VoidF());
        }

//        public Verify(BoolV flip, IntV priority, IntV prioRand) {
//            BoolVe highGrad = tmp(new BoolVe());
//            gt(gradient, IntVe.of(3, gradient.n), highGrad);
//            BoolVe blobVe = tmp(new BoolVe());
//            broadcast(blob, blobVe);
//            and(highGrad, blobVe, highGrad);
//            call(BlobV.send(highGrad, highGrad));
//
//            BoolV cancel = tmp(new BoolV());
//            redOr(highGrad, cancel);
//            not(cancel, cancel);
//            and(cancel, flip, flip);
//        }
    }

    @Override
    public Procedure<VoidF> verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
