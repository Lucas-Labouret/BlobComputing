package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolEf;

/** Represents a reference to a BoolEf value */
public class BoolEfRef extends Ref<BoolEf> {
    private BoolEf field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolEf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEf get() { return field; }

    /** @return a reference to the given BoolEf. */
    public static BoolEfRef of(BoolEf value) {
        BoolEfRef ref = new BoolEfRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEfRef copy() { 
        if (field != null) return BoolEfRef.of(field.copy());
        return new BoolEfRef();
    }
}
