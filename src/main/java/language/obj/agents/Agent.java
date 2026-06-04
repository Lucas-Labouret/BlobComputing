package language.obj.agents;

import language.Obj;
import language.Ref;
import language.instruction.Procedure;
import language.ref.agents.*;
import language.ref.field.boolField.BoolVRef;

public abstract class Agent extends Obj {
    protected BoolVRef state;
    private final AgentRef thisRef = AgentRef.of(this);

    public Agent() { this.state = new BoolVRef(); }
    public Agent(BoolVRef state) { this.state = state; }

    private static class ApplyFlip extends Procedure {
        public <I extends Agent, O extends Agent> ApplyFlip(Ref<I> in, BoolVRef flip, Ref<O> out) {
            BoolVRef notIn = tmp(new BoolVRef());
            not(in.get().state, notIn);

            fif(flip, notIn, in.get().state, out.get().state);
        }
    }
    public static <I extends Agent, O extends Agent> Procedure applyFlip(Ref<I> in, BoolVRef flip, Ref<O> out) { return new ApplyFlip(in, flip, out); }
    public <O extends Agent> Procedure applyFlip(BoolVRef flip, Ref<O> out) { return applyFlip(thisRef, flip, out); }
    public Procedure applyFlip(BoolVRef flip) { return applyFlip(thisRef, flip, thisRef); }
}