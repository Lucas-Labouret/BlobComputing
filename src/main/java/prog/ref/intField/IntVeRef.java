package prog.ref.intField;

import language.Ref;

import prog.obj.intField.IntVe;

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
    public IntVeRef copy() { return IntVeRef.of(field.copy()); }
}

