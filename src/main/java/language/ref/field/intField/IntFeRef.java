package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntFe;

/** Represents a reference to a IntFe value */
public class IntFeRef extends Ref<IntFe> {
    private IntFe field;

    /** Sets the referenced value. */
    @Override
    public void set(IntFe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntFe get() { return field; }

    /** @return a reference to the given IntFe. */
    public static IntFeRef of(IntFe value) {
        IntFeRef ref = new IntFeRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFeRef copy() { 
        if (field != null) return IntFeRef.of(field.copy());
        return new IntFeRef();
    }
}
