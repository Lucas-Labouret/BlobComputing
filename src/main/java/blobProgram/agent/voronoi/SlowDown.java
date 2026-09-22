package blobProgram.agent.voronoi;

import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class SlowDown extends Force {
    private final int nbits;
    private final int factor;

    private final IntVRef counter;

    public SlowDown(int factor, int nbits) {
        this.factor = factor;
        this.nbits = nbits;
        this.counter = new IntVRef(IntV.of(factor-1, nbits));
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            eq(counter, new IntVRef(IntV.of(factor-1, nbits)), no);

            IntVRef nextCounter = new IntVRef(new IntV(nbits));
            add(counter, new IntVRef(IntV.of(1, nbits)), nextCounter);

            fif(no, new IntVRef(IntV.of(0, nbits)), nextCounter, counter);

            not(no, no);
            set(new BoolVRef(BoolV.zeroes()), yes);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
