package language.ref.agent;

import language.Ref;

import language.obj.agent.Agent;

/** Represents a reference to a Agent value */
public class AgentRef extends Ref<Agent> {
    private Agent field;

    /** Sets the referenced value. */
    @Override
    public void set(Agent value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Agent get() { return field; }

    /** @return a reference to the given Agent. */
    public static AgentRef of(Agent value) {
        AgentRef ref = new AgentRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public AgentRef copy() { 
        if (field != null) return AgentRef.of(field.copy());
        return new AgentRef();
    }
}
