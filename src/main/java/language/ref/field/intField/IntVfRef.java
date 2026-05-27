package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntVf;

/** Represents a reference to a IntVf value */
public class IntVfRef extends Ref<IntVf> {
    private IntVf field;

    /** Sets the referenced value. */
    @Override
    public void set(IntVf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntVf get() { return field; }

    /** @return a reference to the given IntVf. */
    public static IntVfRef of(IntVf value) {
        IntVfRef ref = new IntVfRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVfRef copy() { 
        if (field != null) return IntVfRef.of(field.copy());
        return new IntVfRef();
    }
}
