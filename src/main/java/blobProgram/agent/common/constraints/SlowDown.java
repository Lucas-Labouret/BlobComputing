package blobProgram.agent.common.constraints;

import blobProgram.agent.Constraint;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

/**
 * Block all movement except for every factor-th time. This is used to slow down the movement of the blob.
 */
public class SlowDown extends Constraint {
    private final int nbits;
    private final int factor;

    private final IntV counter;

    public SlowDown(int factor, int nbits) {
        this.factor = factor;
        this.nbits = nbits;
        this.counter = IntV.of(factor-1, nbits);
    }

    private class Verify extends Procedure {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            BoolV keepFlip = tmp(new BoolV());
            eq(counter, IntV.of(factor-1, nbits), keepFlip);
            and(keepFlip, flip, flip);

            IntV nextCounter = new IntV(nbits);
            add(counter, IntV.of(1, nbits), nextCounter);

            fif(keepFlip, IntV.of(0, nbits), nextCounter, counter);
        }
    }

    @Override
    public Procedure verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
