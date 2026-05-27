package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntF;

/** Represents a reference to a IntF value */
public class IntFRef extends Ref<IntF> {
    private IntF field;

    /** Sets the referenced value. */
    @Override
    public void set(IntF value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntF get() { return field; }

    /** @return a reference to the given IntF. */
    public static IntFRef of(IntF value) {
        IntFRef ref = new IntFRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFRef copy() { 
        if (field != null) return IntFRef.of(field.copy());
        return new IntFRef();
    }
}
