package blobProgram.agent;

import language.instruction.Procedure;
import blobProgram.Rand;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;

public abstract class Force {
    public static final int priorityBits = 5;
    public static final int prioRandBits = 5;

    public final IntVRef priority;
    public final IntVRef prioRand;

    protected final Rand rand = new Rand();

    protected Force(IntVRef priority) {
        this.priority = priority;
        this.prioRand = new IntVRef(new IntV(prioRandBits));
    }

    private class Compute extends Procedure {
        public Compute(BoolVRef target) {
            rand.next(prioRand);
            call(_compute(target));
        }
    }
    public Procedure compute(BoolVRef target) {
        return new Compute(target);
    }
    protected abstract Procedure _compute(BoolVRef target);
}
