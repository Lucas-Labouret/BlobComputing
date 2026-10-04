package blobProgram.agent;

import blobProgram.Rand;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

public abstract class Force {
    public static final int priorityBits = 2;
    public static final int prioRandBits = 2;

    public final IntV priority;
    public final IntV prioRand;

    protected final Rand rand = new Rand();

    protected Force() {
        this.priority = IntV.of(0, priorityBits);
        this.prioRand = new IntV(prioRandBits);
    }

    protected Force(IntV priority) {
        this.priority = priority;
        this.prioRand = new IntV(prioRandBits);
    }

    public Force setPriority(int p) {
        priority.set(IntV.of(p, priorityBits));
        return this;
    }

    private class Apply extends Procedure {
        public Apply(BoolV yes, BoolV no) {
            call(rand.next(prioRand));
            call(compute(yes, no));
        }
    }
    public Procedure apply(BoolV yes, BoolV no) {
        return new Apply(yes, no);
    }
    protected abstract Procedure compute(BoolV yes, BoolV no);
}
