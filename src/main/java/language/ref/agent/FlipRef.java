package language.ref.agent;

import language.Ref;

import language.obj.agent.Flip;

/** Represents a reference to a Flip value */
public class FlipRef extends Ref<Flip> {
    private Flip field;

    /** Sets the referenced value. */
    @Override
    public void set(Flip value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Flip get() { return field; }

    /** @return a reference to the given Flip. */
    public static FlipRef of(Flip value) {
        FlipRef ref = new FlipRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public FlipRef copy() { 
        if (field != null) return FlipRef.of(field.copy());
        return new FlipRef();
    }
}
