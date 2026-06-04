package language.obj.agents;

import language.Obj;
import language.Ref;
import language.instruction.Procedure;
import language.ref.field.boolField.BoolVeRef;
import language.ref.field.intField.IntVRef;

public abstract class Force extends Obj {
    public final IntVRef priority;
    public final IntVRef prioRand;

    protected Force(IntVRef priority, IntVRef prioRand) {
        this.priority = priority;
        this.prioRand = prioRand;
    }

    public abstract <A extends Agent> Procedure compute(Ref<A> agent, BoolVeRef target);

    @Override
    public Force copy() { return null; }
}
