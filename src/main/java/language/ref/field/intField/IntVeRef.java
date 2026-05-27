package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntVe;

/** Represents a reference to a IntVe value */
public class IntVeRef extends Ref<IntVe> {
    private IntVe field;

    /** Sets the referenced value. */
    @Override
    public void set(IntVe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntVe get() { return field; }

    /** @return a reference to the given IntVe. */
    public static IntVeRef of(IntVe value) {
        IntVeRef ref = new IntVeRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVeRef copy() { 
        if (field != null) return IntVeRef.of(field.copy());
        return new IntVeRef();
    }
}
