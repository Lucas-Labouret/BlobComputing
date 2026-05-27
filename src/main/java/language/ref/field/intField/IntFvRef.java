package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntFv;

/** Represents a reference to a IntFv value */
public class IntFvRef extends Ref<IntFv> {
    private IntFv field;

    /** Sets the referenced value. */
    @Override
    public void set(IntFv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntFv get() { return field; }

    /** @return a reference to the given IntFv. */
    public static IntFvRef of(IntFv value) {
        IntFvRef ref = new IntFvRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFvRef copy() { 
        if (field != null) return IntFvRef.of(field.copy());
        return new IntFvRef();
    }
}
