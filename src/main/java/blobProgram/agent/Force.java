package blobProgram.agent;

import language.instruction.Procedure;
import blobProgram.Rand;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;

public abstract class Force {
    public static final int priorityBits = 2;
    public static final int prioRandBits = 3;

    public final IntVRef priority;
    public final IntVRef prioRand;

    protected final Rand rand = new Rand();

    protected Force() {
        this.priority = new IntVRef(IntV.of(0, priorityBits));
        this.prioRand = new IntVRef(new IntV(prioRandBits));
    }

    protected Force(IntVRef priority) {
        this.priority = priority;
        this.prioRand = new IntVRef(new IntV(prioRandBits));
    }

    public Force setPriority(int p) {
        priority.set(IntV.of(p, priorityBits));
        return this;
    }

    private class Compute extends Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            call(rand.next(prioRand));
            call(_compute(yes, no));
        }
    }
    public Procedure compute(BoolVRef yes, BoolVRef no) {
        return new Compute(yes, no);
    }
    protected abstract Procedure _compute(BoolVRef yes, BoolVRef no);
}
