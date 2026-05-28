package language.ref;

import language.Ref;

import language.obj.Rand;

/** Represents a reference to a Rand value */
public class RandRef extends Ref<Rand> {
    private Rand field;

    /** Sets the referenced value. */
    @Override
    public void set(Rand value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Rand get() { return field; }

    /** @return a reference to the given Rand. */
    public static RandRef of(Rand value) {
        RandRef ref = new RandRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public RandRef copy() { 
        if (field != null) return RandRef.of(field.copy());
        return new RandRef();
    }
}
