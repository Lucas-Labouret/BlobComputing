package language.ref.field.intField;

import language.Ref;

import language.obj.field.intField.IntEf;

/** Represents a reference to a IntEf value */
public class IntEfRef extends Ref<IntEf> {
    private IntEf field;

    /** Sets the referenced value. */
    @Override
    public void set(IntEf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntEf get() { return field; }

    /** @return a reference to the given IntEf. */
    public static IntEfRef of(IntEf value) {
        IntEfRef ref = new IntEfRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntEfRef copy() { 
        if (field != null) return IntEfRef.of(field.copy());
        return new IntEfRef();
    }
}
