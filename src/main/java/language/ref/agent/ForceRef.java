package language.ref.agent;

import language.Ref;

import language.obj.agent.Force;

/** Represents a reference to a Force value */
public class ForceRef extends Ref<Force> {
    private Force field;

    /** Sets the referenced value. */
    @Override
    public void set(Force value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Force get() { return field; }

    /** @return a reference to the given Force. */
    public static ForceRef of(Force value) {
        ForceRef ref = new ForceRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public ForceRef copy() { 
        if (field != null) return ForceRef.of(field.copy());
        return new ForceRef();
    }
}
