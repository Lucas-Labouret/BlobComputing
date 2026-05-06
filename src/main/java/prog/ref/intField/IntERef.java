package prog.ref.intField;

import language.Ref;

import prog.obj.intField.IntE;

/** Represents a reference to a IntE value */
public class IntERef extends Ref<IntE> {
    private IntE field;

    /** Sets the referenced value. */
    @Override
    public void set(IntE value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntE get() { return field; }

    /** @return a reference to the given IntE. */
    public static IntERef of(IntE value) {
        IntERef ref = new IntERef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public IntERef copy() { return IntERef.of(field.copy()); }
}

