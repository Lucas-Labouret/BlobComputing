package language.obj.agent;

import language.Obj;
import language.Ref;
import language.instruction.BasicInstruction;
import language.instruction.Procedure;
import language.ref.agent.*;
import language.ref.field.boolField.BoolVRef;

public abstract class Agent extends Obj {
    private final BoolVRef state;
    private final AgentRef thisRef = AgentRef.of(this);

    public Agent() { this.state = new BoolVRef(); }
    public Agent(BoolVRef state) { this.state = state; }

    public static class GetState implements BasicInstruction {
        private final Ref<? extends Agent> agent;
        private final BoolVRef state;

        public GetState(Ref<? extends Agent> agent, BoolVRef state) {
            this.agent = agent;
            this.state = state;
        }

        @Override
        public boolean exec() {
            state.set(agent.get().state.copy().get());
            return true;
        }
    }
    public static BasicInstruction getState(Ref<? extends Agent> agent, BoolVRef state) { return new GetState(agent, state); }

    public static class SetState implements BasicInstruction {
        private final BoolVRef state;
        private final Ref<? extends Agent> agent;

        public SetState(BoolVRef state, Ref<? extends Agent> agent) {
            this.state = state;
            this.agent = agent;
        }

        @Override
        public boolean exec() {
            agent.get().state.set(state.copy().get());
            return true;
        }
    }
    public static BasicInstruction setState(BoolVRef state, Ref<? extends Agent> agent) { return new SetState(state, agent); }

    private static class ApplyFlip extends Procedure {
        public <I extends Agent, O extends Agent> ApplyFlip(Ref<I> in, FlipRef flip, Ref<O> out) {
            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));
            BoolVRef notIn = tmp(new BoolVRef());
            not(inState, notIn);

            BoolVRef where = tmp(new BoolVRef());
            call(flip.get().where(where));

            fif(where, notIn, inState, inState);
        }
    }
    public static <I extends Agent, O extends Agent> Procedure applyFlip(Ref<I> in, FlipRef flip, Ref<O> out) { return new ApplyFlip(in, flip, out); }
    public <O extends Agent> Procedure applyFlip(FlipRef flip, Ref<O> out) { return applyFlip(thisRef, flip, out); }
    public Procedure applyFlip(FlipRef flip) { return applyFlip(thisRef, flip, thisRef); }

    @Override
    public abstract Agent copy();
}