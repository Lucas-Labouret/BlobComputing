package blobProgram.agent.common.forces;

import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

public class SlowDown extends Force {
    private final int nbits;
    private final int factor;

    private final IntV counter;

    public SlowDown(int factor, int nbits) {
        this.factor = factor;
        this.nbits = nbits;
        this.counter = IntV.of(factor-1, nbits);
    }

    private class Compute extends Procedure {
        public Compute(BoolV yes, BoolV no) {
            eq(counter, IntV.of(factor-1, nbits), no);

            IntV nextCounter = new IntV(nbits);
            add(counter, IntV.of(1, nbits), nextCounter);

            fif(no, IntV.of(0, nbits), nextCounter, counter);

            not(no, no);
            set(new BoolV().zeroes(), yes);
        }
    }

    @Override
    protected Procedure compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
