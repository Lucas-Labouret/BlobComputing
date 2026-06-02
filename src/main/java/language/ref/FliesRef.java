package language.ref;

import language.Ref;

import language.obj.Flies;

/** Represents a reference to a Flies value */
public class FliesRef extends Ref<Flies> {
    private Flies field;

    /** Sets the referenced value. */
    @Override
    public void set(Flies value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Flies get() { return field; }

    /** @return a reference to the given Flies. */
    public static FliesRef of(Flies value) {
        FliesRef ref = new FliesRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public FliesRef copy() { 
        if (field != null) return FliesRef.of(field.copy());
        return new FliesRef();
    }
}
