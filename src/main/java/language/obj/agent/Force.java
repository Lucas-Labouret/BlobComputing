package language.obj.agent;

import language.Obj;
import language.Ref;
import language.instruction.Procedure;
import language.obj.Rand;
import language.obj.field.intField.IntV;
import language.ref.RandRef;
import language.ref.field.boolField.BoolVRef;
import language.ref.field.boolField.BoolVeRef;
import language.ref.field.intField.IntVRef;

public abstract class Force extends Obj {
    public static final int priorityBits = 5;
    public static final int prioRandBits = 5;

    public final IntVRef priority;
    public final IntVRef prioRand;

    protected final Rand rand = new Rand();

    protected Force(IntVRef priority) {
        this.priority = priority;
        this.prioRand = IntVRef.of(new IntV(prioRandBits));
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

    @Override
    public abstract Force copy();
}
