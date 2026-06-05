package language.ref.agent.flies;

import language.Ref;

import language.obj.agent.flies.Expand;

/** Represents a reference to a Expand value */
public class ExpandRef extends Ref<Expand> {
    private Expand field;

    /** Sets the referenced value. */
    @Override
    public void set(Expand value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Expand get() { return field; }

    /** @return a reference to the given Expand. */
    public static ExpandRef of(Expand value) {
        ExpandRef ref = new ExpandRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public ExpandRef copy() { 
        if (field != null) return ExpandRef.of(field.copy());
        return new ExpandRef();
    }
}
