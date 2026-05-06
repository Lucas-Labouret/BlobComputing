package prog.ref.intField;

import language.Ref;

import prog.obj.intField.IntEv;

/** Represents a reference to a IntEv value */
public class IntEvRef extends Ref<IntEv> {
    private IntEv field;

    /** Sets the referenced value. */
    @Override
    public void set(IntEv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntEv get() { return field; }

    /** @return a reference to the given IntEv. */
    public static IntEvRef of(IntEv value) {
        IntEvRef ref = new IntEvRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public IntEvRef copy() { return IntEvRef.of(field.copy()); }
}

