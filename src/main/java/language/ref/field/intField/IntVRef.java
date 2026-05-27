package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntV;

/** Represents a reference to a IntV value */
public class IntVRef extends Ref<IntV> {
    private IntV field;

    /** Sets the referenced value. */
    @Override
    public void set(IntV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntV get() { return field; }

    /** @return a reference to the given IntV. */
    public static IntVRef of(IntV value) {
        IntVRef ref = new IntVRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVRef copy() { 
        if (field != null) return IntVRef.of(field.copy());
        return new IntVRef();
    }
}
